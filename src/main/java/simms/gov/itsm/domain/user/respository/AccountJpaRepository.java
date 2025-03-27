package simms.gov.itsm.domain.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountJapEntity, Long> {

        Optional<AccountJapEntity> findByAccountInfo_UserName(String email);
}
