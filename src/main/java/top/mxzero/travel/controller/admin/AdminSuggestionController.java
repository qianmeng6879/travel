package top.mxzero.travel.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminSuggestionController.class);
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("list")
    public ModelAndView suggestionList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> pageData = suggestionService.split(page, size);

        LOGGER.info("page:{}, total:{}, size:{}", pageData.get("currentPage"), pageData.get("totalPage"), pageData.get("size"));

        mav.addAllObjects(pageData);

        mav.setViewName("admin/user/suggestion_list");
        return mav;
    }
}
