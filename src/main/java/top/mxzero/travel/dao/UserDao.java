package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.User;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
@Mapper
public interface UserDao {

    /**
     * 根据主键获取用户信息
     *
     * @param id 主键值
     * @return 以对象形式返回用户数据
     */
    User findById(Long id);

    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return 以对象形式返回用户数据
     */
    User findByEmail(String email);

    /**
     * 根据邮箱获取用户信息
     *
     * @param phone 手机号码
     * @return 以对象形式返回用户数据
     */
    User findByPhone(String phone);

    /**
     * 返回全部用户信息
     *
     * @return 以List形式返回用户对象数据
     */
    List<User> findAll();

    /**
     * 查询分页数据
     *
     * @param current 偏移量
     * @param size    数据个数
     * @return 以List形式返回用户对象数据
     */
    List<User> findSplit(@Param("current") int current, @Param("size") int size);

    /**
     * 新增用户信息
     *
     * @param user 用户对象
     * @return 返回新增的记录数
     */
    int doCreate(User user);

    /**
     * 修改ID以外的所有数据
     *
     * @param user 用户对象
     * @return 返回修改的记录数
     */
    int doUpdate(User user);

    /**
     * 根据用户ID删除用户信息
     *
     * @param id 用户ID
     * @return 返回删除的记录数
     */
    int doRemove(Long id);

    /**
     * 获取用户数量
     *
     * @return 返回记录数量
     */
    long getCount();

}
