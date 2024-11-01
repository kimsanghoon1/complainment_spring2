package complainment.domain;

import complainment.domain.*;
import complainment.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ComplaintReceived extends AbstractEvent {

    private Long id;
    private String 민원사무번호;
    private String 회원id;
    private ComplainmentDetail 민원상세;

    public ComplaintReceived(Complainment aggregate) {
        super(aggregate);
    }

    public ComplaintReceived() {
        super();
    }
}
//>>> DDD / Domain Event
