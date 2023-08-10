package wizmokeycloak.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import wizmokeycloak.domain.*;

@Component
public class SalesOrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<SalesOrder>> {

    @Override
    public EntityModel<SalesOrder> process(EntityModel<SalesOrder> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updatesalesorder"
                )
                .withRel("updatesalesorder")
        );

        return model;
    }
}
