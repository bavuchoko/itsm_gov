package simms.gov.itsm.domain.authorization;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simms.gov.itsm.config.filter.TokenFilter;
import simms.gov.itsm.domain.authorization.dto.AuthRequestDto;
import simms.gov.itsm.domain.authorization.service.AuthorizationService;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/auth",  produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;


    @PostMapping("/login")
    public ResponseEntity authenticate(
            @RequestBody AuthRequestDto authRequestDto,
            HttpServletResponse response,
            HttpServletRequest request) {

        String accessToken = authorizationService.authorize(authRequestDto,response, request);
        return new ResponseEntity(accessToken, HttpStatus.OK);
    }
}
