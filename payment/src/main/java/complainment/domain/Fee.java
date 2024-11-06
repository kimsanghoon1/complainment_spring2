package complainment.domain;

import complainment.domain.PaymentCompleted;
import complainment.external.User;
import complainment.PaymentApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;

@Entity
@Table(name = "Fee_table")
@Data

// <<< DDD / Aggregate Root
public class Fee {

    @Id
    private Long id;
    private String userId;
    private String applicationNumber;
    private String residentNumber;
    private Long charge;

    @PostPersist
    public void onPostPersist() {

        PaymentCompleted paymentCompleted = new PaymentCompleted(this);
        paymentCompleted.publishAfterCommit();

    }

    public static FeeRepository repository() {
        FeeRepository feeRepository = PaymentApplication.applicationContext.getBean(FeeRepository.class);
        return feeRepository;
    }

    // <<< Clean Arch / Port Method
    public void pay(PayCommand payCommand) {

        // implement business logic here:

        User user = PaymentApplication.applicationContext
                .getBean(complainment.external.UserService.class)
                .getInfo(payCommand.getUserId());

        setId(payCommand.getId());
        setUserId(payCommand.getUserId());
        setResidentNumber(user.getResidentNumber());
        setCharge(500L);

    }

    // >>> Clean Arch / Port Method

}
// >>> DDD / Aggregate Root
