package top.mxzero.travel.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
public class UserRegisterParameterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        String username = request.getParameter("username");
        if (!StringUtils.hasLength(username)) {
            request.setAttribute("usernameError", "用户名为空！");
            flag = false;
        } else {
            request.setAttribute("usernameValue", username);
        }

        String email = request.getParameter("email");

        if (!StringUtils.hasLength(email)) {
            request.setAttribute("emailError", "邮箱为空！");
            flag = false;
        } else {
            request.setAttribute("emailValue", email);
        }

        String password = request.getParameter("password");
        if (!StringUtils.hasLength(password)) {
            request.setAttribute("passwordError", "密码为空！");
            flag = false;
        }

        String rePassword = request.getParameter("rePwd");
        if (!StringUtils.hasLength(rePassword)) {
            request.setAttribute("rePwdError", "确认密码为空");
            flag = false;
        }

        if (!flag) {
            request.getRequestDispatcher("/register").forward(request, response);
        }
        return flag;
    }
}
