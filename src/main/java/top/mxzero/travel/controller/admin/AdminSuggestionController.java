package top.mxzero.travel.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.service.SuggestionService;
import top.mxzero.travel.vo.Suggestion;

import java.util.*;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
@RequestMapping("/admin/suggestion/*")
public class AdminSuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("list")
    public ModelAndView suggestionList() {
        ModelAndView mav = new ModelAndView();

        List<Suggestion> suggestionList = suggestionService.list();

        mav.addObject("suggestionList", suggestionList);
        mav.setViewName("admin/user/suggestion_list");
        return mav;
    }
}
