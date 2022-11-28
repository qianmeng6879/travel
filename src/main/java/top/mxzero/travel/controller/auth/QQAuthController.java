package top.mxzero.travel.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mxzero.travel.service.AuthService;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Controller
public class QQAuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QQAuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("/oauth2/qq_fallback")
    public String qqAuthFallback(
            @RequestParam("code") String code,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "msg", required = false) String msg
    ) {

        // 请求错误
        if (StringUtils.hasLength(msg)) {
            LOGGER.warn("QQ auth error:msg:{},code{}", msg, code);
        }

        LOGGER.info("state:{}", state);

        authService.authorize(code);

        return "redirect:/";
    }
}
