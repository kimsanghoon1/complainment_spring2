package complainment.infra;

import complainment.domain.Complainment;
import complainment.domain.ComplainmentRepository;
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
public class ComplainmentController {

    @Autowired
    ComplainmentRepository complainmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.payment}")
    private String apiUrl;

    @GetMapping("/complainment/validateFee/{id}")
    public ResponseEntity<String> feeStockCheck(
        @PathVariable(value = "id") Long id
    ) {
        String complaintReceivedUrl = apiUrl + "/complaintReceiveds/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> complaintReceivedEntity = restTemplate.exchange(
            complaintReceivedUrl,
            HttpMethod.GET,
            entity,
            String.class
        );

        return complaintReceivedEntity;
    }
}
