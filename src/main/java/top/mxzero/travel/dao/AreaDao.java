package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import top.mxzero.travel.vo.Area;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Mapper
public interface AreaDao {
    Area findById(Integer id);

    Area findByName(String name);

    List<Area> findIsRecommended();

    List<Area> findAll();

    int doCreate(Area area);

    int doRemove(Integer id);

    int doUpdate(Area area);

}
