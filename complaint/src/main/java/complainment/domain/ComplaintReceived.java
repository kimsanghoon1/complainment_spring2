package complainment.domain;

import complainment.domain.*;
import complainment.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ComplaintReceived extends AbstractEvent {

    private Long id;
    private String 민원사무번호;
    private String 회원id;
    private Object 민원상세;
}
