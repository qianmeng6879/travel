package top.mxzero.travel.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
@RequestMapping("/admin/*")
public class LogController {
    @RequestMapping("oplog/list")
    public ModelAndView oplogList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/log/oplog_list");
        return mav;
    }

    @RequestMapping("adminloginlog/list")
    public ModelAndView adminloginlogList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/log/adminloginlog_list");
        return mav;
    }

    @RequestMapping("userloginlog/list")
    public ModelAndView userloginlogList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/log/userloginlog_list");
        return mav;
    }

}
