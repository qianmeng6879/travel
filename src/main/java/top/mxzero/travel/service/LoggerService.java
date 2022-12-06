package top.mxzero.travel.service;

import top.mxzero.travel.vo.LogInfo;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/1
 */
public interface LoggerService {
    void loginLogger(String username, String ip);

    void operatorLogger(LogInfo logInfo);
}
