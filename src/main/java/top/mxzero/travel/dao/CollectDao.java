package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.Collect;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Mapper
public interface CollectDao {

    List<Collect> findAllByUserId(Long id);

    int doCreate(Collect collect);

    int doRemove(Collect collect);

    int isExists(Collect collect);

    long getCount(Long userId);
}
