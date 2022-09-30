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
@RequestMapping("/admin/travels/*")
public class AdminTravelsController {

    @RequestMapping("list")
    public ModelAndView travelsList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/travels/travels_list");
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView travelsAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/travels/travels_add");
        return mav;
    }
}
