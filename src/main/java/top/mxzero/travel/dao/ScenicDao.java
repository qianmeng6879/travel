package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.Scenic;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Mapper
public interface ScenicDao {
    Scenic findById(Integer id);

    List<Scenic> findIsRecommended();

    List<Scenic> findIsHot();

    List<Scenic> findByAreaIdAndRecommended(Integer areaId);

    List<Scenic> findAll();

    List<Scenic> findSplit(@Param("current") int current, @Param("size") int size);

    List<Scenic> findSplitByAreaIdAndStar(
            @Param("current") int current,
            @Param("size") int size,
            @Param("areaId") Integer areaId,
            @Param("star") Integer star
    );

    int doCreate(Scenic scenic);

    int doUpdate(Scenic scenic);

    int doRemove(Integer id);

    int getCount();

    int getCountByAreaIdAndStar(
            @Param("areaId") Integer areaId,
            @Param("star") Integer star
    );

}
