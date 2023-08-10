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
// @RequestMapping(value="/products")
@Transactional
public class ProductController {

    @Autowired
    ProductRepository productRepository;
}
