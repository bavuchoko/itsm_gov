package simms.gov.itsm.domain.task.service;

import simms.gov.itsm.domain.task.dto.ClaimResponse;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.user.entity.Account;

public interface ClaimService {


    void saveClaim(Account user, TaskRequestDto body);

    ClaimResponse getClaim(Account user, Long id);

    void acceptClaim(Account user, Long id);
}
