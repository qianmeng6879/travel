package top.mxzero.travel.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/27
 */
public class IpUtil {
    private IpUtil() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddr = request.getHeader("x-forwarded-for");
        if (!StringUtils.hasLength(ipAddr)) {
            ipAddr = request.getRemoteAddr();
        }

        return ipAddr;
    }
}
