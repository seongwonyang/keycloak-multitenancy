package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import wizmokeycloak.domain.*;
import wizmokeycloak.infra.AbstractEvent;

@Data
@ToString
public class CompanyCreated extends AbstractEvent {

    private String code;
    private String name;
    private String industry;
    private Date foundedDate;

    public CompanyCreated(Company aggregate) {
        super(aggregate);
    }

    public CompanyCreated() {
        super();
    }
}
