package top.mxzero.travel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.mxzero.travel.service.LoggerService;
import top.mxzero.travel.vo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoggerService loggerService;

    @GetMapping("/login")
    public String loginPage() {
//        publisher.publishEvent(new MessageEvent(this, "hello world"));
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(User user) {
        LOGGER.info("user:{}", user);
        return "/";
    }

    @GetMapping("/loginv2")
    public Object loginV2(User user, HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if(ip == null){
            ip = request.getRemoteAddr();
        }

        loggerService.loginLogger(user.getUsername(), ip);
        return "loginv2";
    }
}
