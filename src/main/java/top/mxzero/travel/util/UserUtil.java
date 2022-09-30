package top.mxzero.travel.util;

import org.springframework.stereotype.Component;
import top.mxzero.travel.vo.User;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Component
public class UserUtil {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    private UserUtil() {
    }

    public static User currentUser() {
        return threadLocal.get();
    }

    public static void set(User user) {
        threadLocal.set(user);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
