package top.mxzero.travel.service;

import top.mxzero.travel.vo.User;

import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
public interface UserService {

    User get(Long id);

    User getByEmail(String email);

    User getByPhone(String phone);

    List<User> list();

    Map<String, Object> split(int page, int size);

    boolean save(User user);

    boolean update(User user);

    boolean remove(Long id);

    long count();
}
