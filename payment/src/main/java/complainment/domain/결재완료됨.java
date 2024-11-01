package complainment.domain;

import complainment.domain.*;
import complainment.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class 결재완료됨 extends AbstractEvent {

    private Long id;
    private String applicationNumber;
    private Long charge;

    public 결재완료됨(Fee aggregate) {
        super(aggregate);
    }

    public 결재완료됨() {
        super();
    }
}
//>>> DDD / Domain Event
