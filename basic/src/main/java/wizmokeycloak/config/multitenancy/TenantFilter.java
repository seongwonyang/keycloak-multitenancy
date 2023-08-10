package wizmokeycloak.config.multitenancy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
//import newtest.security.AuthenticationService;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
class TenantFilter implements Filter {

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String token =
            ((HttpServletRequest) request).getHeader("Authorization");
        if (token == null) {
            throw new ServletException("No Token provided");
        }

        String tenant = JwtTokenParser.extractAudienceFromToken(
            token.replace("Bearer ", "")
        );

        TenantContext.setCurrentTenant(tenant);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setCurrentTenant("");
        }
    }
}
