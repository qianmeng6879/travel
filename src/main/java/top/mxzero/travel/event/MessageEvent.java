package top.mxzero.travel.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/1
 */
public class MessageEvent extends ApplicationEvent {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEvent.class);

    private String message;

    public MessageEvent(Object target, String message) {
        super(target);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessage() {
        LOGGER.info("message:{}", this.message);
    }
}
