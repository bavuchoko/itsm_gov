package simms.gov.itsm.config;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import simms.gov.itsm.config.filter.TokenAccessDeniedHandler;
import simms.gov.itsm.config.filter.TokenAuthenticationEntryPoint;
import simms.gov.itsm.config.token.TokenManager;
import simms.gov.itsm.config.token.TokenSecurityConfig;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenManager tokenManager;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    @Value("${path.host}")
    private String CORS_PATH;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public SecurityConfig(TokenManager tokenManager, TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint, TokenAccessDeniedHandler tokenAccessDeniedHandler) {
        this.tokenManager = tokenManager;
        this.tokenAuthenticationEntryPoint = tokenAuthenticationEntryPoint;
        this.tokenAccessDeniedHandler = tokenAccessDeniedHandler;
    }

    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(List.of(CORS_PATH, "http://localhost:3000","http://192.168.0.2:3000"));
            config.setAllowCredentials(true);
            config.setExposedHeaders(List.of("X-License" ,"X-platform","Content-Disposition"));

            return config;
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic(HttpBasicConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(configuer ->
                        configuer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/**").permitAll()
                )
                .exceptionHandling(authenticationManager ->
                        authenticationManager
                                .authenticationEntryPoint(tokenAuthenticationEntryPoint)
                                .accessDeniedHandler(tokenAccessDeniedHandler)
                )
                .with(new TokenSecurityConfig(tokenManager), Customizer.withDefaults());

        return httpSecurity.build();
    }


}
