package complainment.domain;

import complainment.ComplaintApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Complaint_table")
@Data
//<<< DDD / Aggregate Root
public class Complaint {

    @Id
    private String complainId;

    private String result;
    private ComplainmentDetail detail;

    @PostPersist
    public void onPostPersist() {}

    public static ComplaintRepository repository() {
        ComplaintRepository complaintRepository = ComplaintApplication.applicationContext.getBean(
            ComplaintRepository.class
        );
        return complaintRepository;
    }

    //<<< Clean Arch / Port Method
    public static void applyComplain(ComplaintReceived complaintReceived) {
        Complaint complaint = new Complaint();
        complaint.setComplainId(complaintReceived.getComplainId());
        complaint.setDetail(complaintReceived.getComplainDetail());
        repository().save(complaint);
        //implement business logic here:

        /** Example 1:  new item 
        

        */

        /** Example 2:  finding and process
        
        repository().findById(complaintReceived.get???()).ifPresent(complaint->{
            
            complaint // do something
            repository().save(complaint);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
