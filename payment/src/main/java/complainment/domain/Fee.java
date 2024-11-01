package complainment.domain;

import complainment.domain.결재완료됨;
import complainment.PaymentApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Fee_table")
@Data

//<<< DDD / Aggregate Root
public class Fee  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String 접수번호;
    
    
    
    
    private Long 수수료;

    @PostPersist
    public void onPostPersist(){


        결재완료됨 결재완료됨 = new 결재완료됨(this);
        결재완료됨.publishAfterCommit();

    
    }

    public static FeeRepository repository(){
        FeeRepository feeRepository = PaymentApplication.applicationContext.getBean(FeeRepository.class);
        return feeRepository;
    }



//<<< Clean Arch / Port Method
    public void pay(PayCommand payCommand){
        
        //implement business logic here:
        

        User user = FeeApplication.applicationContext
            .getBean(complainment.external.UserService.class)
            .getInfo(get??);

    }
//>>> Clean Arch / Port Method



}
//>>> DDD / Aggregate Root
