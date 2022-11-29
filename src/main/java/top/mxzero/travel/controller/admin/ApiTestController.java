package top.mxzero.travel.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/29
 */
@RestController
@RequestMapping("/admin/api/*")
public class ApiTestController {
    @GetMapping("test")
    public Object test(){
        return Map.of("message", "success", "code", 200);
    }
}
