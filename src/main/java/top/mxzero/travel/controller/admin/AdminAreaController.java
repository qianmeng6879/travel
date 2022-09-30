package top.mxzero.travel.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.vo.Area;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
@RequestMapping("/admin/area/*")
public class AdminAreaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping("list")
    public ModelAndView areaListPage() {
        List<Area> list = areaService.list();

        ModelAndView mav = new ModelAndView();
        mav.addObject("areaList", list);
        mav.setViewName("admin/area/area_list");
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView areaAddPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/area/area_add");
        return mav;
    }

    @PostMapping("add.action")
    public ModelAndView areaAddAction(Area area) {
        LOGGER.info("area:{}", area);

        ModelAndView mav = new ModelAndView("admin/area/area_add");

        try {
            boolean result = areaService.save(area);
            if (result) {
                mav.addObject("message", "新增地区成功");
            } else {
                mav.addObject("error", "新增地区失败");
            }
        } catch (ServiceException e) {
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    @RequestMapping("view/{id}")
    public ModelAndView viewAreaPage(@PathVariable("id") Integer id) {
        Area area = areaService.get(id);

        if (area == null) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            requestAttributes.getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        ModelAndView mav = new ModelAndView("admin/area/area_edit");
        mav.addObject("area", area);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editAreaAction(@PathVariable("id") Integer id, Area area, Model model) {
        Area currentArea = areaService.get(id);

        if (area == null) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            requestAttributes.getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        area.setId(id);
        boolean result = areaService.update(area);

        if (result) {
            model.addAttribute("message", "地区信息修改成功");
        } else {
            model.addAttribute("error", "地区修改失败");
        }
        return String.format("forward:/admin/area/view/%d", id);
    }
}
