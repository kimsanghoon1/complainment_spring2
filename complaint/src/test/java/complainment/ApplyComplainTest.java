package complainment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import complainment.config.kafka.KafkaProcessor;
import complainment.domain.Complaint;
import complainment.domain.ComplainmentDetail;
import complainment.domain.ComplaintReceived;
import complainment.domain.ComplaintRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplyComplainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            ApplyComplainTest.class);

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public ComplaintRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() throws InterruptedException {
        // given:
        Complaint entity = new Complaint();

        entity.setComplainId("1");
        entity.setResult("N/A");

        repository.save(entity);

        // when:

        ComplaintReceived event = new ComplaintReceived();

        event.setId(1L);
        event.setComplainId("N/A");
        event.setUserId("N/A");
        ComplainmentDetail complainDetail = new ComplainmentDetail();
        complainDetail.setDescription("test");
        event.setComplainDetail(complainDetail);

        ComplaintApplication.applicationContext = applicationContext;

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
        try {
            String msg = objectMapper.writeValueAsString(event);

            processor
                    .inboundTopic()
                    .send(
                            MessageBuilder
                                    .withPayload(msg)
                                    .setHeader(
                                            MessageHeaders.CONTENT_TYPE,
                                            MimeTypeUtils.APPLICATION_JSON)
                                    .setHeader("type", event.getEventType())
                                    .build());
            // then:
            Message<String> received = (Message<String>) messageCollector
            .forChannel(processor.outboundTopic())
            .poll(3, TimeUnit.SECONDS);

            assertNotNull("Resulted event must be published", received);

            LOGGER.info("Response received: {}", received.getPayload());
            ComplaintReceived outputEvent = objectMapper.readValue(received.getPayload(), ComplaintReceived.class);
            assertEquals(String.valueOf(outputEvent.getId()), 1L);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
