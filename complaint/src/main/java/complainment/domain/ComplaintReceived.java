package complainment.domain;

import complainment.domain.*;
import complainment.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ComplaintReceived extends AbstractEvent {

    private Long id;
    private String complainId;
    private String userId;
    private Object complainDetail;
}
