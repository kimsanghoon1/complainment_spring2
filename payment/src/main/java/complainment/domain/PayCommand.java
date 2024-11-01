package complainment.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PayCommand {

    private Long id;
    private String applicationNumber;
    private Long charge;
}
