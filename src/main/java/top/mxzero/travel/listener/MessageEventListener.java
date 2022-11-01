package top.mxzero.travel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.mxzero.travel.event.MessageEvent;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/1
 */
@Component
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void onApplicationEvent(MessageEvent event) {
        LOGGER.info("MessageEvent 事件触发");
        event.sendMessage();
    }
}
