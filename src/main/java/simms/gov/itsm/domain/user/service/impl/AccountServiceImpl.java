package simms.gov.itsm.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simms.gov.itsm.domain.user.dto.AccountRequestDto;
import simms.gov.itsm.domain.user.dto.AccountResponseDto;
import simms.gov.itsm.domain.user.entity.Account;
import simms.gov.itsm.domain.user.mapper.AccountMapper;
import simms.gov.itsm.domain.user.respository.AccountJpaRepository;
import simms.gov.itsm.domain.user.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaRepository accountJpaRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AccountResponseDto saveAccount(AccountRequestDto requestDto) {

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Account entity = accountJpaRepository.save(
                AccountMapper.INSTANCE.requestToEntity(requestDto)
        );

        return  AccountMapper.INSTANCE.entityToResponse(entity);
    }
}
