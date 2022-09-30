package top.mxzero.travel.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mxzero.travel.dao.SuggestionDao;
import top.mxzero.travel.service.SuggestionService;
import top.mxzero.travel.vo.Suggestion;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
@Controller
public class ContactController {
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("/contact")
    public String contactPage() {
        return "home/contact";
    }

    @PostMapping("/contact/suggestion")
    public String sendSuggestion(Suggestion suggestion, Model model) {
        boolean result = suggestionService.save(suggestion);
        if (result) {
            model.addAttribute("message", "发送建议成功!");
        } else {
            model.addAttribute("error", "发送建议失败!");
        }
        return "forward:/contact";
    }
}
