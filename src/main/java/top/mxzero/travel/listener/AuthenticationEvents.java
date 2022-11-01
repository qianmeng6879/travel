package top.mxzero.travel.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;


@Configuration
public class AuthenticationEvents {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEvents.class);

    @EventListener
    public void onSuccess(InteractiveAuthenticationSuccessEvent success) {
        LOGGER.info("success event {}", success.getAuthentication().getName());
    }
}