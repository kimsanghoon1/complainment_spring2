package complainment.infra;

import complainment.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class FeeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Fee>> {

    @Override
    public EntityModel<Fee> process(EntityModel<Fee> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/pay")
                .withRel("pay")
        );

        return model;
    }
}
