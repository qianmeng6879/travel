package top.mxzero.travel.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.controller.abs.AbstractBaseAction;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.Area;
import top.mxzero.travel.vo.Scenic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Controller
public class IndexController extends AbstractBaseAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AreaService areaService;
    @Autowired
    private ScenicService scenicService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("home/index");

        List<Scenic> hotScenic = scenicService.getIsHot();
        mav.addObject("hotScenic", hotScenic);

        List<Area> areaList = areaService.list();
        mav.addObject("areaList", areaList);


        List<Area> areaListIsRecommended = areaService.getIsRecommended(3);

        List<Map<String, Object>> recommendedData = new ArrayList<>();
        areaListIsRecommended.forEach(area -> {
            Map<String, Object> data = new HashMap<>();
            data.put("area", area);
            List<Scenic> scenicServiceByAreaIdAndRecommended = scenicService.getByAreaIdAndRecommended(area.getId());
            data.put("scenicList", scenicServiceByAreaIdAndRecommended);
            recommendedData.add(data);
        });

        mav.addObject("recommendedData", recommendedData);
        return mav;
    }
}
