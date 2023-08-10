package wizmokeycloak.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wizmokeycloak.domain.*;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository
    extends PagingAndSortingRepository<Company, String> {
    @Query(
        value = "select company " +
        "from Company company " +
        "where(:name is null or company.name like %:name%) and (:industry is null or company.industry like %:industry%) and (:foundedDate is null or company.foundedDate like %:foundedDate%)"
    )
    List<Company> findByCompanyQuery(
        String name,
        String industry,
        Date foundedDate,
        Pageable pageable
    );
}
