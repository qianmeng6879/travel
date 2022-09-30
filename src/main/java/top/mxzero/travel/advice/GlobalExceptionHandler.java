package top.mxzero.travel.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        return null;
    }
}
