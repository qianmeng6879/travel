package top.mxzero.travel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.RestData;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/6
 */
@RestController
@RequestMapping("/api/scenic/*")
public class ScenicApiController {

    @Autowired
    private ScenicService service;

    @GetMapping("list")
    public RestData list(){
        return RestData.success(service.list());
    }

    @GetMapping("get")
    public RestData get(@RequestParam("id") Integer id){
        return RestData.success(service.get(id));
    }
}
