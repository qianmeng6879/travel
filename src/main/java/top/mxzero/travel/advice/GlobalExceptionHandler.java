package top.mxzero.travel.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage());
        Map<String,Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        map.put("type", e.getClass().getName());

        return map;
    }
}
