package top.mxzero.travel;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */

@Configuration
@ComponentScan(value = {"top.mxzero.travel.service","top.mxzero.travel.config"})
public class SpringConfig {
}
