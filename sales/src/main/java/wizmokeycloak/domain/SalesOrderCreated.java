package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import wizmokeycloak.domain.*;
import wizmokeycloak.infra.AbstractEvent;

@Data
@ToString
public class SalesOrderCreated extends AbstractEvent {

    private String salesOrderNumber;
    private String salesPerson;
    private SalesType salesType;
    private CompanyId companyId;
    private List<SalesItem> salesItems;

    public SalesOrderCreated(SalesOrder aggregate) {
        super(aggregate);
    }

    public SalesOrderCreated() {
        super();
    }
}
