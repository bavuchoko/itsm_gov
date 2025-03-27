package simms.gov.itsm.domain.user.service;

import simms.gov.itsm.domain.user.dto.AccountRequestDto;
import simms.gov.itsm.domain.user.dto.AccountResponseDto;

public interface AccountService {
        AccountResponseDto saveAccount(AccountRequestDto requestDto);
}
