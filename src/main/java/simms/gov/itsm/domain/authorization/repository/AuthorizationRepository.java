package simms.gov.itsm.domain.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<AccountJapEntity, Long> {

    @Query("SELECT  a  FROM Account a LEFT JOIN FETCH a.roles WHERE BINARY(a.accountInfo.userName) = :username")
    Optional<AccountJapEntity> findByUsernameWithRolesAndDepartmentAndCompany(@Param("username") String username);
}
