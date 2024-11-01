package complainment.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class PayCommand {

    @Id
    private Long id;

    private String applicationNumber;
    private Long charge;
}
