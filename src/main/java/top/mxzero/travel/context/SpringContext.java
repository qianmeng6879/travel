package top.mxzero.travel.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/3
 */
@Configuration
@ComponentScans({
        @ComponentScan("top.mxzero.travel.service"),
        @ComponentScan("top.mxzero.travel.config"),
        @ComponentScan("top.mxzero.travel.listener")
})
public class SpringContext{
}
