package com.example.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import complainment.ReceiptApplication;
import complainment.config.kafka.KafkaProcessor;
import complainment.domain.Complainment;
import complainment.domain.ComplainmentDetail;
import complainment.domain.ComplainmentRepository;
import complainment.domain.ComplaintReceived;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=ReceiptApplication.class)
@AutoConfigureMessageVerifier
public class ApplyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyTest.class);

    @Autowired
    private KafkaProcessor processor;
    
    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MessageVerifier<Message<?>> messageVerifier;

    @Autowired
    public ComplainmentRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() throws InterruptedException {

        // given:
        Complainment existingEntity = new Complainment();

        existingEntity.setId(1L);
        existingEntity.setComplainId("C1001");
        existingEntity.setUserId("U1001");
        ComplainmentDetail complainDetail = new ComplainmentDetail();
        complainDetail.setDescription("complain");
        existingEntity.setComplainDetail(complainDetail);

        repository.save(existingEntity);

        // when:

        try {

            ComplaintReceived newEntity = new ComplaintReceived();

            newEntity.setId(1L);
            newEntity.setComplainId("C1001");
            newEntity.setUserId("U1001");
            newEntity.setComplainDetail(complainDetail);

            // repository.save(newEntity);
            String payload = objectMapper.writeValueAsString(newEntity);
            processor
                .outboundTopic()
                .send(
                    MessageBuilder
                        .withPayload(payload)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", newEntity.getEventType())
                        .build()
                );

            //then:

            Message<String> received = (Message<String>) messageCollector.forChannel(processor.outboundTopic()).poll(5, TimeUnit.SECONDS);

            assertNotNull("Resulted event must be published", received);

            // then:
            String receivedPayload = (String) received.getPayload();

            ComplaintReceived outputEvent = objectMapper.readValue(receivedPayload, ComplaintReceived.class);

            LOGGER.info("Response received: {}", outputEvent);

            assertEquals(outputEvent.getId(), Long.valueOf(1L));
            assertEquals(outputEvent.getComplainId(), "C1001");
            assertEquals(outputEvent.getUserId(), "U1001");
            assertEquals(outputEvent.getComplainDetail(), complainDetail);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }

    }

}
