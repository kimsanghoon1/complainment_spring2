package complainment.infra;

import complainment.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ComplainmentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Complainment>> {

    @Override
    public EntityModel<Complainment> process(EntityModel<Complainment> model) {
        return model;
    }
}
