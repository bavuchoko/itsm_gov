package simms.gov.itsm.config.token;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface TokenManager {

    String createToken(Authentication authentication, TokenType tokenType);
    boolean validateToken(String token);
    Authentication getAuthentication(String token);
    void logout(String refreshToken);

}
