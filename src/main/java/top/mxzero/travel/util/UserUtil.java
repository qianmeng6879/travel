package top.mxzero.travel.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.mxzero.travel.vo.User;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Component
public class UserUtil {

    private UserUtil() {
    }

    public static User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;

        if (!authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }

        if (authentication instanceof User) {
            user = (User) authentication;
        } else if (authentication.getPrincipal() instanceof User) {
            user = (User) authentication.getPrincipal();
        } else {
            user = (User) authentication.getDetails();
        }
        return user;
    }
}
