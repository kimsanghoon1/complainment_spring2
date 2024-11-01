package complainment.infra;

import complainment.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ComplaintHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Complaint>> {

    @Override
    public EntityModel<Complaint> process(EntityModel<Complaint> model) {
        return model;
    }
}
