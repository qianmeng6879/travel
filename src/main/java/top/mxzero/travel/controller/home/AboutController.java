package top.mxzero.travel.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
@Controller
public class AboutController {
    @RequestMapping("/about")
    public String aboutPage() {
        return "home/about";
    }
}
