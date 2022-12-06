package top.mxzero.travel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.LoggerMapper;
import top.mxzero.travel.service.LoggerService;
import top.mxzero.travel.vo.LogInfo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/1
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerMapper loggerMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerServiceImpl.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void loginLogger(String username, String ip) {
        LocalDateTime current = LocalDateTime.now();
        String content = String.format("登录日志：%s %s %s", username, ip, dtf.format(current));
        LOGGER.info(content);
        LogInfo logInfo = new LogInfo();
        logInfo.setContent(content);
        logInfo.setType(1);
        logInfo.setIpAddr(ip);
        Date currentDate = Date.from(current.atZone(ZoneId.systemDefault()).toInstant());
        logInfo.setCreateTime(currentDate);

        loggerMapper.insert(logInfo);
    }

    @Override
    public void operatorLogger(LogInfo logInfo) {

    }
}
