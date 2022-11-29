package top.mxzero.travel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import top.mxzero.travel.filter.CustomAuthenticationFilter;
import top.mxzero.travel.service.impl.UserDetailServiceImpl;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(((request, response, authentication) -> {
            request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                Map<String, Object> map = new HashMap<>();
                map.put("sessionId", request.getSession().getId());
                map.put("message", "认证成功");
                try (Writer writer = response.getWriter()) {
                    writer.write(new ObjectMapper().writeValueAsString(map));
                }
            } else {
                DefaultSavedRequest saved_request = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
                if (saved_request != null && saved_request.getMethod().equals("GET")) {
                    response.sendRedirect(saved_request.getRequestURL());
                } else {
                    response.sendRedirect("/");
                }
            }
        }));

        filter.setAuthenticationFailureHandler(((request, response, exception) -> {
            LOGGER.info("message:{}", exception.getMessage());
            LOGGER.info("exception:{}", exception.getClass().getName());
            LOGGER.info("content-type:{}", request.getContentType());
            String contentType = request.getContentType();
            if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
                try (Writer writer = response.getWriter()) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    writer.write(String.format("{\"error\": \"%s\", \"code\": 401}", exception.getMessage()));
                }
            } else {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
                response.sendRedirect("/login");
            }
        }));

        return filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests().antMatchers("/user/**").authenticated()
                .and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/admin/pwd/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/collect/**").authenticated()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessHandler(((request, response, authentication) -> {
                    if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        try (PrintWriter writer = response.getWriter()) {
                            writer.write("{\"message\": \"注销成功\", \"code\": 200}");
                        }
                    } else {
                        response.sendRedirect("/login?logout");
                    }
                }))
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("hello");
        LOGGER.info("password:{}", password);
        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}
