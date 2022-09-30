package top.mxzero.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.travel.controller.abs.AbstractBaseAction;
import top.mxzero.travel.service.UserService;

import java.security.Principal;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@RestController
@RequestMapping("/user/*")
public class UserController extends AbstractBaseAction {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("current")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
