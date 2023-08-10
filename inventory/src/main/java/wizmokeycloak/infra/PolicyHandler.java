package wizmokeycloak.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import wizmokeycloak.config.kafka.KafkaProcessor;
import wizmokeycloak.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    InventoryRepository inventoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SalesOrderCreated'"
    )
    public void wheneverSalesOrderCreated_UpdateInventory(
        @Payload SalesOrderCreated salesOrderCreated
    ) {
        SalesOrderCreated event = salesOrderCreated;
        System.out.println(
            "\n\n##### listener UpdateInventory : " + salesOrderCreated + "\n\n"
        );

        // Sample Logic //
        Inventory.updateInventory(event);
    }
}
