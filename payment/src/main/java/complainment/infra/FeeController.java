package complainment.infra;

import complainment.domain.Fee;
import complainment.domain.FeeRepository;
import complainment.domain.PayCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FeeController {

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(
        value = "fees/{id}/pay",
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
