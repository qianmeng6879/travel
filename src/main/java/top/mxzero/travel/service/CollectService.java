package top.mxzero.travel.service;

import top.mxzero.travel.vo.Collect;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
public interface CollectService {
    /**
     * 收藏列表
     *
     * @return 以List封装收藏信息
     */
    List<Collect> list(Long userId);

    /**
     * 判断是否已经收藏
     *
     * @param collect 收藏信息
     * @return 已收藏返回true，否则返回false
     */
    boolean isExists(Collect collect);

    /**
     * 新增用户与景区收藏信息
     *
     * @param collect 收藏对象
     * @return 新增成功返回true，否则返回false
     */
    boolean save(Collect collect);

    /**
     * 删除用户与收藏信息
     *
     * @param collect 收藏对象信息
     * @return 删除成功返回true，否则返回false
     */
    boolean remove(Collect collect);

    /**
     * 用户收藏景区数量
     *
     * @param userId 用户ID
     * @return 返回用户收藏景区的数量
     */
    long count(Long userId);
}
