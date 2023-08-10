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
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}
}
