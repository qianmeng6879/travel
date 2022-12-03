package top.mxzero.travel.controller.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/10/13
 */
@Controller
public class Error404 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Error404.class);

    @RequestMapping("/404")
    public String code404(HttpServletRequest request) {
        String contentType = request.getContentType();
        LOGGER.info("content-type:{}", contentType);
        return "home/404";
    }
}

