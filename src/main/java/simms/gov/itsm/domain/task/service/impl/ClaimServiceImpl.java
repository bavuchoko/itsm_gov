package simms.gov.itsm.domain.task.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simms.gov.itsm.domain.task.Claim;
import simms.gov.itsm.domain.task.TaskFactory;
import simms.gov.itsm.domain.task.dto.ClaimResponse;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.task.mapper.TaskMapper;
import simms.gov.itsm.domain.task.repository.ClaimJpaRepository;
import simms.gov.itsm.domain.task.service.ClaimService;
import simms.gov.itsm.domain.user.entity.Account;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimJpaRepository claimJpaRepository;
    private final TaskFactory taskFactory;

    @Override
    public void saveClaim(Account user, TaskRequestDto body) {
        Claim claim = taskFactory.claimOf(body);
        claimJpaRepository.save(claim);
    }

    @Override
    public ClaimResponse getClaim(Account user, Long id) {
        Claim claim = claimJpaRepository.findById(id).orElseThrow(()->new RuntimeException("request not found"));
        return TaskMapper.INSTANCE.toClaimResponse(claim);
    }

    @Override
    public void acceptClaim(Account user, Long id) {
        Claim claim = claimJpaRepository.findById(id).orElseThrow(()->new RuntimeException("request not found"));
        claim.accept(user);
    }


}
