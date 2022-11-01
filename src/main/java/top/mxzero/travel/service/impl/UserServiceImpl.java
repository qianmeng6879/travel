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
    public Map<String, Object> split(int currentPage, int pageSize) {

        List<User> split = userDao.findSplit((currentPage - 1) * pageSize, pageSize);
        long count = userDao.getCount();

        Map<String, Object> result = new HashMap<>();
        result.put("data", split);
        result.put("dataSize", split.size());
        result.put("currentPage", currentPage);
        result.put("pageSize", pageSize);
        result.put("totalPage", (count % pageSize != 0) ? count / pageSize + 1 : count / pageSize);

        return result;
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
