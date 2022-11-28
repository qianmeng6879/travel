package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.QQAuthInfo;
import top.mxzero.travel.vo.WeiboAuthInfo;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/11/28
 */
@Mapper
public interface WeiboAuthInfoDao {
    WeiboAuthInfo selectById(Long id);

    WeiboAuthInfo selectByWeiboId(String wbId);

    WeiboAuthInfo selectByUserId(String userId);

    List<WeiboAuthInfo> selectList();

    List<WeiboAuthInfo> selectSplit(@Param("current") long current, @Param("suze") long size);

    int insert(WeiboAuthInfo authInfo);

    int update(WeiboAuthInfo authInfo);

    int deleteById(Long id);

    int deleteByUserId(Long userId);
}
