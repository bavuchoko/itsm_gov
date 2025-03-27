package simms.gov.itsm.config.token;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import simms.gov.itsm.domain.authorization.dto.AuthAdapter;
import simms.gov.itsm.domain.authorization.repository.AuthorizationRepository;
import simms.gov.itsm.domain.authorization.service.AuthorizationService;
import simms.gov.itsm.domain.user.respository.AccountJpaRepository;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenManagerImpl implements TokenManager, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenManagerImpl.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long accessTokenValidityTime;

    private final AuthorizationRepository authorizationRepository;

    @Value("${spring.jwt.token-validity-one-min}")
    private long globalTimeOneMin;

    private Key key;





    public TokenManagerImpl(
            @Value("${spring.jwt.secret}") String secret,
            @Value("${spring.jwt.token-validity-one-min}") long tokenValidityOneMin,
            TokenIpCollection Loginmap,
            AuthorizationRepository authorizationRepository) {
        this.secret = secret;
        // 30분
        this.accessTokenValidityTime = tokenValidityOneMin * 100 ;
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String createToken(Authentication authentication, TokenType tokenType) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Long id = ((AuthAdapter)(authentication.getPrincipal())).getAccount().getId();
        String userName = ((AuthAdapter)(authentication.getPrincipal())).getAccount().getAccountInfo().getUserName();
        String name = ((AuthAdapter)(authentication.getPrincipal())).getAccount().getAccountInfo().getName();

        long remainingMilliseconds = getRemainingMilliseconds();

        //액세스토큰 유효기간 30분
        long now = (new Date()).getTime();
        Date validity =  tokenType == TokenType.ACCESS_TOKEN ?  new Date(now + this.accessTokenValidityTime) : new Date(now + remainingMilliseconds);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put(AUTHORITIES_KEY, authorities);
        payloads.put("id", id);
        payloads.put("username", userName);
        payloads.put("name", name);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(payloads)
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .compact();
    }

    private static long getRemainingMilliseconds() {
        // 갱신토큰 유효기간 오늘 자정까지
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(nowTime.toLocalDate(), LocalTime.MAX);
        Duration remainingTime = Duration.between(nowTime, midnight);
        long remainingMilliseconds = remainingTime.toMillis();
        return remainingMilliseconds;
    }


    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        String username = claims.get("username", String.class);


        UserDetails userDetails=  new AuthAdapter(authorizationRepository.findByUsernameWithRolesAndDepartmentAndCompany(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }



    @Override
    public void logout(String refreshToken) {
    }


    @Override
    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        }

    }

}