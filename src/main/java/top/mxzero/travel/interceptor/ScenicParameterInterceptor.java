package top.mxzero.travel.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Statement;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
public class ScenicParameterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        String title = request.getParameter("title");
        if (!StringUtils.hasLength(title)) {
            request.setAttribute("titleError", "标题为空");
            flag = false;
        } else {
            request.setAttribute("titleValue", title);
        }

        String areaId = request.getParameter("areaId");
        if (!StringUtils.hasLength(areaId)) {
            request.setAttribute("areaError", "未选择地区");
            flag = false;
        } else {
            request.setAttribute("areaIdValue", areaId);
        }

        String address = request.getParameter("address");
        if (!StringUtils.hasLength(address)) {
            request.setAttribute("addressError", "景区地址为为空");
            flag = false;
        } else {
            request.setAttribute("addressValue", address);
        }

        String star = request.getParameter("star");
        if (!StringUtils.hasLength(star)) {
            request.setAttribute("starError", "未选择星级");
            flag = false;
        } else {
            request.setAttribute("starValue", star);
        }

        String introduction = request.getParameter("introduction");
        if (!StringUtils.hasLength(introduction)) {
            request.setAttribute("introductionError", "景区简介为空");
            flag = false;
        } else {
            request.setAttribute("introductionValue", introduction);
        }

        String content = request.getParameter("content");
        if (!StringUtils.hasLength(content)) {
            request.setAttribute("contentError", "景区内容为空");
            flag = false;
        } else {
            request.setAttribute("contentValue", content);
        }

        if (!flag) {
            request.getRequestDispatcher("/admin/scenic/add").forward(request, response);
        }

        return flag;
    }
}
