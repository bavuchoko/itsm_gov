package simms.gov.itsm.domain.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simms.gov.itsm.domain.task.Claim;

public interface ClaimJpaRepository extends JpaRepository<Claim, Long> {
}
