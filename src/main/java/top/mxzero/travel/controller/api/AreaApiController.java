package top.mxzero.travel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.vo.Area;
import top.mxzero.travel.vo.RestData;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/6
 */
@RestController
@RequestMapping("/api/areas/*")
public class AreaApiController {
    @Autowired
    private AreaService areaService;


    @GetMapping("get")
    public RestData get(@RequestParam("id") Integer id){
        Area area = areaService.get(id);
        if(area == null){
            return RestData.notFound();
        }
        return RestData.success(area);
    }

    @GetMapping("list")
    public RestData list(){

        return RestData.success(areaService.list());
    }
}
