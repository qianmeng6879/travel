package top.mxzero.travel.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.vo.User;

import javax.print.DocFlavor;
import java.security.Principal;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Controller
@RequestMapping("/admin/*")
public class PasswordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping("pwd")
    public String changePasswordPage() {
        return "admin/pwd";
    }

    @PostMapping("pwd/change")
    public String changePasswordAction(
            @RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword,
            Model model,
            Principal principal
    ) {
        if (!StringUtils.hasLength(oldPassword)) {
            model.addAttribute("oldPasswordError", "旧密码为空!");
            return "forward:/admin/pwd";
        }

        if (!StringUtils.hasLength(newPassword)) {
            model.addAttribute("newPasswordError", "新密码为空!");
            return "forward:/admin/pwd";
        }

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.get(((User) token.getPrincipal()).getId());

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("error", "旧密码错误");
            return "forward:/admin/pwd";
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        try {
            boolean result = userService.update(user);
            if (result) {
                model.addAttribute("message", "密码修改成功！");
            } else {
                model.addAttribute("error", "密码修改失败！");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "forward:/admin/pwd";
    }
}
