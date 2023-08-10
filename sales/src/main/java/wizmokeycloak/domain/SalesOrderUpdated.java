package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import wizmokeycloak.domain.*;
import wizmokeycloak.infra.AbstractEvent;

@Data
@ToString
public class SalesOrderUpdated extends AbstractEvent {

    private String salesOrderNumber;
    private String salesPerson;
    private SalesType salesType;
    private CompanyId companyId;
    private List<SalesItem> salesItems;

    public SalesOrderUpdated(SalesOrder aggregate) {
        super(aggregate);
    }

    public SalesOrderUpdated() {
        super();
    }
}
