package complainment.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "${api.url.user}")
public interface UserService {
    @GetMapping(path = "/users/{id}")
    public User getInfo(@PathVariable("id") Long id);
}
