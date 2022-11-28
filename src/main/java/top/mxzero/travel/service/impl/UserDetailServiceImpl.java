package top.mxzero.travel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.UserDao;
import top.mxzero.travel.vo.User;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByEmail(username);
        LOGGER.info("user:{}", user);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }
}
