package complainment.infra;

import complainment.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/fees")
@Transactional
public class FeeController {

    @Autowired
    FeeRepository feeRepository;

    @RequestMapping(
        value = "/fees/{id}/pay",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Fee pay(
        @PathVariable(value = "id") Long id,
        @RequestBody PayCommand payCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /fee/pay  called #####");
        Optional<Fee> optionalFee = feeRepository.findById(id);

        optionalFee.orElseThrow(() -> new Exception("No Entity Found"));
        Fee fee = optionalFee.get();
        fee.pay(payCommand);

        feeRepository.save(fee);
        return fee;
    }
}
//>>> Clean Arch / Inbound Adaptor
