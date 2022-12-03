package top.mxzero.travel.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.RestData;
import top.mxzero.travel.vo.User;

import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/3
 */

@RestController
@RequestMapping("/api/users/*")
public class UserApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public RestData listUser() {
        List<User> list = userService.list();
        return RestData.success(list);
    }

    @GetMapping("split")
    public RestData splitUser(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size

    ) {
        Map<String, Object> split = userService.split(page, size);
        return RestData.success(split);
    }

    @GetMapping("get/{id}")
    public RestData listUser(@PathVariable("id") Long id) {
        User user = userService.get(id);
        if (user == null) {
            return RestData.notFound();
        }
        return RestData.success(user);
    }

    @PostMapping("add")
    public RestData addUser(@RequestBody User user) {
        boolean result = userService.save(user);
        if (result) {
            return RestData.success(result);
        }

        return RestData.fail("注册失败");
    }
}


