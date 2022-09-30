package top.mxzero.travel.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.service.CollectService;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.Collect;
import top.mxzero.travel.vo.Scenic;
import top.mxzero.travel.vo.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Controller
public class ScenicDetailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScenicDetailController.class);

    @Autowired
    private ScenicService scenicService;

    @Autowired
    private CollectService collectService;

    @RequestMapping("/scenic/{id}")
    public ModelAndView viewScenic(@PathVariable("id") Integer id) {
        Scenic scenic = scenicService.get(id);
        if (scenic == null) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        // 判断当前用户是否登录
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            User user = (User) authentication.getPrincipal();
            Collect collect = new Collect();
            collect.setScenicId(scenic.getId());
            collect.setUserId(user.getId());

            // 设置是否收藏
            scenic.setCollect(collectService.isExists(collect));
        }

        ModelAndView mav = new ModelAndView("home/travel_info");
        mav.addObject("scenic", scenic);
        return mav;
    }

}
