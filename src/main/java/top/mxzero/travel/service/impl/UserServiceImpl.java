package top.mxzero.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.UserDao;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.UserService;
import top.mxzero.travel.util.PasswordUtil;
import top.mxzero.travel.vo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User get(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public Map<String, Object> split(int page, int size) {

        List<User> userList = userDao.findSplit((page - 1) * size, size);
        long count = userDao.getCount();

        Map<String, Object> map = new HashMap<>();
        map.put("data", userList);
        map.put("cp", page);
        map.put("size", userList.size());
        map.put("total", count);
        if (count % size == 0) {
            map.put("totalPage", count / size);
        } else {
            map.put("totalPage", count / size + 1);
        }

        return map;
    }

    @Override
    public boolean save(User user) {
        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new ServiceException("该邮箱已注册");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDao.doCreate(user) > 0;
    }

    @Override
    public boolean update(User user) {
        return userDao.doUpdate(user) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return userDao.doRemove(id) > 0;
    }

    @Override
    public long count() {
        return userDao.getCount();
    }
}
