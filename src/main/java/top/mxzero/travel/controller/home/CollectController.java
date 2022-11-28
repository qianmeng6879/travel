package top.mxzero.travel.controller.home;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.service.CollectService;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.util.UserUtil;
import top.mxzero.travel.vo.Collect;
import top.mxzero.travel.vo.Scenic;
import top.mxzero.travel.vo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Controller
@RequestMapping("/collect/*")
public class CollectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private CollectService collectService;

    @Autowired
    private ScenicService scenicService;

    @RequestMapping("list")
    public ModelAndView collectList() throws Exception {
        User user = UserUtil.currentUser();

        List<Collect> collectList = collectService.list(user.getId());

        List<Scenic> scenicList = new ArrayList<>();

        collectList.forEach(collect -> {
            Scenic scenic = scenicService.get(collect.getScenicId());
            scenicList.add(scenic);
        });

        ModelAndView mav = new ModelAndView("home/collect_list");
        mav.addObject("scenicList", scenicList);

        return mav;
    }

    /**
     * 用户取消收藏
     *
     * @param id 景区ID
     * @return 取消成功返回ok=1
     */
    @ResponseBody
    @RequestMapping("cancel")
    public Object cancelCollect(@RequestParam("id") Integer id) {
        User user = UserUtil.currentUser();
        Collect collect = new Collect();
        collect.setUserId(user.getId());
        collect.setScenicId(id);

        Map<String, Object> map = new HashMap<>();

        // 判断是否收藏
        if (!collectService.isExists(collect)) {
            map.put("ok", 0);
            map.put("message", "未收藏");
            return map;
        }

        // 取消收藏操作
        boolean result = collectService.remove(collect);
        map.put("result", result);
        map.put("ok", 1);
        map.put("message", "取消收藏。");
        return map;
    }

    /**
     * 收藏景区接口
     *
     * @param collect 接收json格式数据
     * @return 收藏成功返回ok=1
     */
    @ResponseBody
    @PostMapping("collect")
    public Object collectScenic(Collect collect) {
        LOGGER.info("collect:{}", collect);
        User user = UserUtil.currentUser();

        collect.setUserId(user.getId());

        Map<String, Object> map = new HashMap<>();

        // 判断景区是否已经收藏
        if (collectService.isExists(collect)) {
            map.put("ok", 0);
            map.put("message", "该景区已收藏");
            return map;
        }

        // 景区未收藏时新增收藏信息
        boolean result = collectService.save(collect);
        map.put("result", result);
        map.put("ok", 1);
        map.put("message", "操作成功");

        return map;
    }
}
