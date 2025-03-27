package simms.gov.itsm.domain.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import simms.gov.itsm.domain.authorization.entity.AuthJpaEntity;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountJapEntity, Long> {

        Optional<AuthJpaEntity> findByAccountInfo_UserName(String email);
}
