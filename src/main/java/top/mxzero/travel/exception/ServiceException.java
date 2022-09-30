package top.mxzero.travel.exception;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
