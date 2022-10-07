package top.mxzero.travel.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.Area;
import top.mxzero.travel.vo.Scenic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Controller
@RequestMapping("/admin/scenic/*")
public class AdminScenicController {
    private static final String FILE_UPLOAD_DIR;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("fileupload");
        FILE_UPLOAD_DIR = resourceBundle.getString("file.upload.dir");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminScenicController.class);
    @Autowired
    private ScenicService scenicService;
    @Autowired
    private AreaService areaService;

    /**
     * 景区列表
     *
     * @param page 当前页
     * @param size 每页大小
     */
    @RequestMapping("list")
    public ModelAndView scenicListPage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Map<String, Object> split = scenicService.split(page, size);
        ModelAndView mav = new ModelAndView();
        mav.addAllObjects(split);
        mav.setViewName("admin/scenic/scenic_list");
        return mav;
    }

    /**
     * 修改景区信息
     *
     * @param id 要修改的景区ID
     */
    @PutMapping("edit/{id}")
    public String editScenicAction(@PathVariable("id") Integer id, Scenic scenic, Model model) {
        return "redirect:/admin/scenic/edit/" + id;
    }

    /**
     * 删除景区
     *
     * @param id 景区ID
     */
    @DeleteMapping("remove/{id}")
    public String removeScenicAction(@PathVariable("id") Integer id, RedirectAttributes model) {
        boolean result = scenicService.remove(id);
        if (result) {
            model.addAttribute("message", "删除成功");
        } else {
            model.addAttribute("error", "删除失败");
        }
        return "redirect:/admin/scenic/list";
    }

    @RequestMapping("edit/{id}")
    public ModelAndView editScenicPage(@PathVariable("id") Integer id) {
        Scenic scenic = scenicService.get(id);
        if (scenic == null) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        List<Area> areaList = areaService.list();
        ModelAndView mav = new ModelAndView("admin/scenic/scenic_edit");
        mav.addObject("areaList", areaList);
        mav.addObject("scenic", scenic);
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView scenicAddPage() {
        List<Area> areaList = areaService.list();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/scenic/scenic_add");
        mav.addObject("areaList", areaList);
        return mav;
    }

    @PostMapping("add.action")
    public String scenicAddAction(MultipartFile photo, Scenic scenic) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (photo.isEmpty()) {
            request.setAttribute("photoError", "未选择封面图");
            return "forward:/admin/scenic/add";
        }

        String filename = saveFile(photo);

        scenic.setImageUrl(filename);
        try {
            boolean result = scenicService.save(scenic);
            if (result) {
                request.setAttribute("message", "新增景区成功！");
            } else {
                request.setAttribute("error", "新增景区失败！");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("error", "新增景区失败！" + e.getMessage());
        }
        return "forward:/admin/scenic/add";
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileType = file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
        String filename = "scenic/" + UUID.randomUUID().toString() + "." + fileType;
        file.transferTo(new File(FILE_UPLOAD_DIR + filename));
        return filename;
    }
}
