package top.mxzero.travel.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */

public class QQAuthFilter extends AbstractAuthenticationProcessingFilter {
    protected QQAuthFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String msg = request.getParameter("msg");


        return null;
    }
}
