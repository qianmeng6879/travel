package top.mxzero.travel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import top.mxzero.travel.service.LoggerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/1
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerServiceImpl.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void loginLogger(String username, String ip) {
        LOGGER.info("登录日志：{} {} {}", username, ip, dtf.format(LocalDateTime.now()));
    }
}
