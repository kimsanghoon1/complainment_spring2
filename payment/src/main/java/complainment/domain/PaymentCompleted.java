package complainment.domain;

import complainment.domain.*;
import complainment.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PaymentCompleted extends AbstractEvent {

    private Long id;
    private String applicationNumber;
    private Long charge;

    public PaymentCompleted(Fee aggregate) {
        super(aggregate);
    }

    public PaymentCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
