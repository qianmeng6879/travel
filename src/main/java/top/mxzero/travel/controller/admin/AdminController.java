package top.mxzero.travel.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }
}
