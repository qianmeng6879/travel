package top.mxzero.travel.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
public class SuggestionParameterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        String name = request.getParameter("name");
        if (!StringUtils.hasLength(name)) {
            request.setAttribute("nameError", "联系人姓名为空");
            flag = false;
        } else {
            request.setAttribute("nameValue", name);
        }

        String email = request.getParameter("email");
        if (!StringUtils.hasLength(email)) {
            request.setAttribute("emailError", "联系人邮箱为空");
            flag = false;
        } else {
            request.setAttribute("emailValue", email);
        }

        String content = request.getParameter("content");
        if (!StringUtils.hasLength(content)) {
            request.setAttribute("contentError", "内容为空");
            flag = false;
        } else {
            request.setAttribute("contentValue", content);
        }

        if (!flag) {
            request.getRequestDispatcher("/contact").forward(request, response);
        }

        return flag;
    }
}
