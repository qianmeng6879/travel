package top.mxzero.travel.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mxzero.travel.service.AuthService;
import top.mxzero.travel.vo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Controller
public class WeiboAuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeiboAuthController.class);
    @Autowired
    @Qualifier("weiboAuthService")
    private AuthService authService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @GetMapping("/oauth2/wb_fallback")
    public String qqAuthFallback(
            @RequestParam("code") String code,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "msg", required = false) String msg,
            HttpServletRequest request
    ) {

        // 请求错误
        if (StringUtils.hasLength(msg)) {
            LOGGER.warn("Weibo auth error:msg:{},code{}", msg, code);
        }

        User user = authService.authorize(code);

        LOGGER.info("controller:{}", user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        token.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(token);

        return "redirect:/";
    }
}
