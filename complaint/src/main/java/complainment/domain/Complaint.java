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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long complainId;

    private String result;

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
        //implement business logic here:

        /** Example 1:  new item 
        Complaint complaint = new Complaint();
        repository().save(complaint);

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
