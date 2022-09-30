package top.mxzero.travel.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证新增地区的参数是否为空
 *
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
public class AreaParameterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        String name = request.getParameter("name");
        if (!StringUtils.hasLength(name)) {
            request.setAttribute("nameError", "地区名称为空");
            flag = false;
        }else {
            request.setAttribute("nameValue", name);
        }

        String introduction = request.getParameter("introduction");
        if (!StringUtils.hasLength(introduction)) {
            request.setAttribute("introductionError", "地区简介为空");
            flag = false;
        }else {
            request.setAttribute("introductionValue", introduction);
        }

        if (!StringUtils.hasLength(request.getParameter("recommended"))) {
            request.setAttribute("recommendedError", "请选择是否推荐");
            flag = false;
        }

        if (!flag) {
            request.getRequestDispatcher("/admin/area/add").forward(request, response);
        }

        return flag;
    }
}
