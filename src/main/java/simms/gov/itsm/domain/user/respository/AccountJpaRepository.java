package simms.gov.itsm.domain.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import simms.gov.itsm.domain.user.entity.Account;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

        Optional<Account> findByAccountInfo_UserName(String email);
}
