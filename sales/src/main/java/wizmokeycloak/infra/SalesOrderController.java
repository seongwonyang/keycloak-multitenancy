package wizmokeycloak.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wizmokeycloak.domain.*;

@RestController
// @RequestMapping(value="/salesOrders")
@Transactional
public class SalesOrderController {

    @Autowired
    SalesOrderRepository salesOrderRepository;

    @RequestMapping(
        value = "salesOrders/{id}/updatesalesorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public SalesOrder updateSalesOrder(
        @PathVariable(value = "id") String id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /salesOrder/updateSalesOrder  called #####");
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(
            id
        );

        optionalSalesOrder.orElseThrow(() -> new Exception("No Entity Found"));
        SalesOrder salesOrder = optionalSalesOrder.get();
        salesOrder.updateSalesOrder();

        salesOrderRepository.save(salesOrder);
        return salesOrder;
    }
}
