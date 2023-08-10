package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import wizmokeycloak.domain.*;
import wizmokeycloak.infra.AbstractEvent;

@Data
@ToString
public class CompanyUpdated extends AbstractEvent {

    private String code;
    private String name;
    private String industry;
    private Date foundedDate;

    public CompanyUpdated(Company aggregate) {
        super(aggregate);
    }

    public CompanyUpdated() {
        super();
    }
}
