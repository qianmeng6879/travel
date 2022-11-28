package top.mxzero.travel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import top.mxzero.travel.service.impl.UserDetailServiceImpl;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests().antMatchers("/user/**").authenticated()
                .and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/admin/pwd/**").hasRole("ADMIN")
//                .and().authorizeRequests().antMatchers("/scenic/**").authenticated()
                .and().authorizeRequests().antMatchers("/collect/**").authenticated()
                .and().formLogin().loginPage("/login")
//                .loginProcessingUrl("/login").failureHandler((request, response, exception) -> {
//                    LOGGER.info("message:{}", exception.getMessage());
//                    LOGGER.info("exception:{}", exception.getClass().getName());
//                    response.sendRedirect("/login");
//                })
                .and().csrf().disable()
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
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        return new ProviderManager(provider);
    }
}
