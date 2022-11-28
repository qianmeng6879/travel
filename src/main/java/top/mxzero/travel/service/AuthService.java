package top.mxzero.travel.service;

import top.mxzero.travel.vo.User;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
public interface AuthService {
    User authorize(String code);

}
