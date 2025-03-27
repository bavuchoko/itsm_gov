package simms.gov.itsm.domain.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import simms.gov.itsm.domain.authorization.entity.AuthJpaEntity;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<AuthJpaEntity, Long> {

    @Query("SELECT a FROM Account a WHERE BINARY(a.accountInfo.userName) = :username")
    Optional<AuthJpaEntity> findByUsernameWithRolesAndDepartmentAndCompany(@Param("username") String username);
}
