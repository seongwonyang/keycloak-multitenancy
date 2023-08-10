package wizmokeycloak.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import wizmokeycloak.SalesApplication;
import wizmokeycloak.domain.SalesOrderCreated;
import wizmokeycloak.domain.SalesOrderDeleted;

@Entity
@Table(name = "SalesOrder_table")
@Data
public class SalesOrder {

    @Id
    private String salesOrderNumber;

    private String salesPerson;

    @Embedded
    private CompanyId companyId;

    @ElementCollection
    private List<SalesItem> salesItems;

    private SalesType salesType;

    @PostPersist
    public void onPostPersist() {
        SalesOrderCreated salesOrderCreated = new SalesOrderCreated(this);
        salesOrderCreated.publishAfterCommit();

        SalesOrderDeleted salesOrderDeleted = new SalesOrderDeleted(this);
        salesOrderDeleted.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static SalesOrderRepository repository() {
        SalesOrderRepository salesOrderRepository = SalesApplication.applicationContext.getBean(
            SalesOrderRepository.class
        );
        return salesOrderRepository;
    }

    public void updateSalesOrder() {
        //implement business logic here:

        SalesOrderUpdated salesOrderUpdated = new SalesOrderUpdated(this);
        salesOrderUpdated.publishAfterCommit();
    }
}
