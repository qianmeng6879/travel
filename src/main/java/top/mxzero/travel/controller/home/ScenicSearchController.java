package top.mxzero.travel.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.Area;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Controller
public class ScenicSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScenicSearchController.class);

    @Autowired
    private ScenicService scenicService;

    @Autowired
    private AreaService areaService;

    @RequestMapping("/search")
    public ModelAndView searchPage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "6") int size,
            @RequestParam(value = "area_id", required = false) Integer areaId,
            @RequestParam(value = "star", required = false) Integer star
    ) {
        LOGGER.info("page:{}", page);
        LOGGER.info("size:{}", size);
        LOGGER.info("areaId:{}", areaId);
        LOGGER.info("star:{}", star);

        ModelAndView mav = new ModelAndView("home/search");
        mav.addObject("pageValue", page);
        mav.addObject("sizeValue", size);
        mav.addObject("areaIdValue", areaId);
        mav.addObject("starValue", star);


        List<Area> areaList = areaService.list();
        mav.addObject("areaList", areaList);
        // 只传递分页数据时
        if (areaId == null && star == null) {
            Map<String, Object> data = scenicService.split(page, size);
            mav.addAllObjects(data);
            return mav;
        }

        if (areaId != null) {
            Map<String, Object> data = scenicService.splitByAreaIdAndStar(page, size, areaId, star);
            mav.addAllObjects(data);
            return mav;
        }

        Map<String, Object> map = scenicService.splitByAreaIdAndStar(page, size, areaId, star);

        mav.addAllObjects(map);
        return mav;
    }
}
