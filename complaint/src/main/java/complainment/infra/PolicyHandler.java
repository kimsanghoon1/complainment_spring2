package complainment.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import complainment.config.kafka.KafkaProcessor;
import complainment.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ComplaintRepository complaintRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ComplaintReceived'"
    )
    public void wheneverComplaintReceived_ApplyComplain(
        @Payload ComplaintReceived complaintReceived
    ) {
        ComplaintReceived event = complaintReceived;
        System.out.println(
            "\n\n##### listener ApplyComplain : " + complaintReceived + "\n\n"
        );

        // Sample Logic //
        Complaint.applyComplain(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
