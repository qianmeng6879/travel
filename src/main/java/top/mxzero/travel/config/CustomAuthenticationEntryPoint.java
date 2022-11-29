package top.mxzero.travel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            try(PrintWriter out = response.getWriter()){
                out.write("{\"error\": \"未登录\", \"code\": 401}");
            }
        } else {
            LOGGER.info("requestUri:{}", request.getRequestURI());
            response.sendRedirect("/login");
        }

    }
}