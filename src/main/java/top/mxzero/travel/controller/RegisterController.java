package top.mxzero.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.User;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String registerPage(User user) {
        return "register";
    }

    @PostMapping("/register.action")
    public ModelAndView registerHandler(User user, @RequestParam("rePwd") String rePwd) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");

        if (!user.getPassword().equals(rePwd)) {
            mav.addObject("rePwdError", "两次密码不一致！");
            return mav;
        }

        try {
            boolean result = userService.save(user);
            mav.addObject("registerSuccess", result);
        } catch (ServiceException exception) {
            mav.addObject("error", exception.getMessage());
        }

        return mav;
    }
}
