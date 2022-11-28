package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.QQAuthInfo;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Mapper
public interface QQAuthInfoDao {
    QQAuthInfo selectById(Long id);

    QQAuthInfo selectByOpenId(String openId);

    QQAuthInfo selectByUserId(String userId);

    List<QQAuthInfo> selectList();

    List<QQAuthInfo> selectSplit(@Param("current") long current, @Param("suze") long size);

    int insert(QQAuthInfo authInfo);

    int update(QQAuthInfo authInfo);

    int deleteById(Long id);

    int deleteByUserId(Long userId);
}
