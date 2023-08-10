package wizmokeycloak.domain;

import java.util.*;
import lombok.*;
import wizmokeycloak.domain.*;
import wizmokeycloak.infra.AbstractEvent;

@Data
@ToString
public class SalesOrderCreated extends AbstractEvent {

    private String salesOrderNumber;
    private String salesPerson;
    private Object salesType;
    private Object companyId;
    private Object salesItems;
}
