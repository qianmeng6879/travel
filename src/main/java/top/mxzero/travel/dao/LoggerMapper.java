package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.LogInfo;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/6
 */
@Mapper
public interface LoggerMapper {
    List<LogInfo> selectList();

    List<LogInfo> selectListByType(@Param("type") Integer type);

    List<LogInfo> selectSplitByType(@Param("current") int page, @Param("size") int size, @Param("type") Integer type);

    int insert(LogInfo logInfo);

    int delete(Long id);

}
