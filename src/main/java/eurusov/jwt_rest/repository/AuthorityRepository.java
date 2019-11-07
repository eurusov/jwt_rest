package eurusov.jwt_rest.repository;

import eurusov.jwt_rest.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByAuthority(String authority);

    boolean existsByAuthority(String authority);
}
