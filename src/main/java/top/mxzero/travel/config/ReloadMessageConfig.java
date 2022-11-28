package top.mxzero.travel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

//security中文提示信息配置类
@Configuration
public class ReloadMessageConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReloadMessageConfig.class);

    @Bean //加载中文认证提示信息
    public ReloadableResourceBundleMessageSource messageSource() throws Exception {
        Locale.setDefault(Locale.CHINA);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //加载org/springframework/security包下的中文提示信息 配置文件
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");

        return messageSource;
    }
}
