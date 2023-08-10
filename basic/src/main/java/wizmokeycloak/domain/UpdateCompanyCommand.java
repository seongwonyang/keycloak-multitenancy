package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateCompanyCommand {

    private String name;
    private String industry;
    private Date foundedDate;
}
