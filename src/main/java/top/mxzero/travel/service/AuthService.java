package top.mxzero.travel.service;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
public interface AuthService {
    public String getAccessToken(String code);

    public String getOpenId();

}
