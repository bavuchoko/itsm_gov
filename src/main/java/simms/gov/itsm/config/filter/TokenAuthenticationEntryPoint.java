package simms.gov.itsm.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {



            // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
            request.getAttribute("error");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String,Object> errMsg = new HashMap<>();
            errMsg.put("status",401);
            errMsg.put("code", HttpStatus.UNAUTHORIZED);
            errMsg.put("message","유효한 자격증명이 필요합니다.");
            try (OutputStream os = response.getOutputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(os, errMsg);
                os.flush();
            }
    }
}