package top.mxzero.travel.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/10/13
 */
@Controller
public class Error404 {
    @RequestMapping("/404")
    public String code404(){
        return "home/404";
    }
}

