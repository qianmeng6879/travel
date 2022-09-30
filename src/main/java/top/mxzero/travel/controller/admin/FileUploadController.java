package top.mxzero.travel.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
@RestController
public class FileUploadController {
    @PostMapping("/admin/ckupload/")
    public Object upload() {
        return null;
    }
}
