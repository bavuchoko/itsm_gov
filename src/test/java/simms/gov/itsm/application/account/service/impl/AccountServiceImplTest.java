package simms.gov.itsm.application.account.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import simms.gov.itsm.domain.user.dto.AccountRequestDto;
import simms.gov.itsm.domain.user.entity.Account;
import simms.gov.itsm.domain.user.respository.AccountJpaRepository;
import simms.gov.itsm.domain.user.service.impl.AccountServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@SpringBootTest
class AccountServiceImplTest {

    @Mock
    private AccountJpaRepository accountJpaRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("계정 생성테스트")
    public void test_saveAccount() {
        /* given */
        AccountRequestDto requestDto = AccountRequestDto.builder()
                .name("테스트")
                .userName("test")
                .email("email@email.com")
                .password("password")
                .build();

        /* when */
        accountService.saveAccount(requestDto);

        /* then */
        verify(passwordEncoder).encode("password");
        verify(accountJpaRepository).save(any(Account.class));
    }

}