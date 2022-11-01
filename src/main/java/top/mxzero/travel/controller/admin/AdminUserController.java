package top.mxzero.travel.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
@RequestMapping("/admin/user/*")
public class AdminUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public ModelAndView userList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {

        Map<String, Object> split = userService.split(page, size);
        ModelAndView mav = new ModelAndView();
        mav.addAllObjects(split);
        mav.setViewName("admin/user/user_list");

        return mav;
    }

    @RequestMapping("view/{id}")
    public ModelAndView userView(@PathVariable("id") Long id) {
        User user = userService.get(id);
        if (user == null) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            requestAttributes.getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        LOGGER.info("username:{}", user.getUsername());

        ModelAndView mav = new ModelAndView("admin/user/user_view");
        mav.addObject("user", user);
        return mav;
    }

}
