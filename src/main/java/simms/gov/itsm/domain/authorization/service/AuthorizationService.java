package simms.gov.itsm.domain.authorization.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import simms.gov.itsm.config.token.TokenManager;
import simms.gov.itsm.config.token.TokenType;
import simms.gov.itsm.domain.authorization.dto.AuthAdapter;
import simms.gov.itsm.domain.authorization.dto.AuthRequestDto;
import simms.gov.itsm.domain.authorization.entity.AuthJpaEntity;
import simms.gov.itsm.domain.authorization.repository.AuthorizationRepository;
import simms.gov.itsm.utils.CookieUtil;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorizationRepository authorizationRepository;
    private final TokenManager tokenManager;
    private final CookieUtil cookieUtil;

    public String authorize(AuthRequestDto account, HttpServletResponse response, HttpServletRequest request) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account.getUserName(),  account.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String accessToken = tokenManager.createToken(authentication, TokenType.ACCESS_TOKEN);
        String refreshToken = tokenManager.createToken(authentication, TokenType.REFRESH_TOKEN);

        cookieUtil.addCookie(response, "refreshToken", refreshToken);
        return accessToken;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthJpaEntity userAccount = authorizationRepository.findByUsernameWithRolesAndDepartmentAndCompany(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthAdapter(userAccount);
    }
}
