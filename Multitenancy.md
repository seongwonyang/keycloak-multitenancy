# Spring-boot 기반의 Multitenancy.

## What Does Multitenant Mean?

애플리케이션을 구축할때, 직접 호스트하고 애플리케이션에서 서비스를 제공하되, 다른 회사의 데이터는 분리하여 여러 회사에 제공하려고 합니다.
제공하기 위해서 다양한 옵션이 있습니다. 가장 간단한 방법은 데이터베이스를 포함한 애플리케이션을 여러 번 배포하는 것입니다.여러개의 서비스가 생기면 관리하기가 힘들어 집니다.
데이터를 분리하는 방법중 하나인 tenant 별로 데이터베이스 가지는 방법입니다.

## Partitioned data.


### DB정보
tenant 별로 DB정보를 세팅 합니다. tenant_1.properties, tenant_2.properties

### DB정보를 tenant설정
- MultitenantConfiguration.java 에서 properties 가져와서 세팅합니다.
```java
    try {
        tenantProperties.load(new FileInputStream(propertyFile));
        String tenantId = tenantProperties.getProperty("name");

        dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
        dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
        dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
        dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
        resolvedDataSources.put(tenantId, dataSourceBuilder.build());
      } catch (IOException exp) {
          throw new RuntimeException("Problem in tenant datasource:" + exp);
      }
```

### Partitioning separation
- TenantContext.java 에서는 ThreadLocal를 이용한 tenantId를 분리하여 파티셔닝을 가집니다.
```java
public class TenantContext {

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void setCurrentTenant(String tenant) {
        CURRENT_TENANT.set(tenant);
    }
}
```
### Partitioning selection
- TenantFilter.java 에서는 어떤 tenantId를 filtering하여 유저의 tenant 위치를 세팅 합니다.
- MultitenantConfiguration에서 요청한 MultitenantDataSource에서 해당 TenantFilter를 이용하여 tenant 구분합니다.

```java
@Component
@Order(1)
class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

        String tenant = AuthenticationService.getTenant((HttpServletRequest) request);
        TenantContext.setCurrentTenant(tenant);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setCurrentTenant("");
        }

    }
}
```

### Select tenant By user jwt
- 로그인한 유저의 jwt에 의해서 tanat정보를 결정하게 됩니다.
```java
public static Authentication getAuthentication(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser()
              .setSigningKey(SIGNINGKEY)
              .parseClaimsJws(token.replace(PREFIX, ""))
              .getBody()
              .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }
```

## Running
### Kafka server and DB running
```
cd kafka
docker-compose up
```

### Create DB
```
docker exec -it infra-db-1 /bin/sh
psql --username=postgres
CREATE DATABASE tenant1;
CREATE DATABASE tenant2;

// show list of databases
\l
```

## Run the backend micro-services

## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Getting Token
```
http :8082/login username="user" password="baeldung"  #getting token
http :8082/login username="admin" password="baeldung"  #getting token

```

## Call service
```
- Token정보 없이 바로 호출시 401 (Unauthorized) 리턴됩니다.
http :8088/serivce id="id"

- 발급된 Token 정보와 함께 호출시
http :8088/serivce id="id" "Authorization: Bearer eqsddx...."

해당 유저의 tenant따른 저장된 DB정보가 호출됩ㄴ디ㅏ.
```

