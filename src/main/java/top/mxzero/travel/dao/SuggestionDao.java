package top.mxzero.travel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.travel.vo.Suggestion;

import java.util.List;

/**
 * 用户建议信息DAO操作接口
 *
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
@Mapper
public interface SuggestionDao {

    /**
     * 根据主键获取详细信息
     *
     * @param id 主键值
     * @return 以VO类型封装返回结果
     */
    Suggestion findById(Long id);

    /**
     * 查询全部信息
     *
     * @return 以List形式返回VO对象数据
     */
    List<Suggestion> findAll();

    /**
     * 分页查询信息
     *
     * @param current 当前偏移量
     * @param size    获取的数据数量
     * @return 以List形式返回VO对象数据
     */
    List<Suggestion> findSplit(@Param("current") int current, @Param("size") int size);


    /**
     * 新增记录
     *
     * @param suggestion VO封装的数据
     * @return 返回新增的记录数量
     */
    int doCreate(Suggestion suggestion);

    /**
     * 根据主键删除记录
     *
     * @param id 主键值
     * @return 返回删除影响的记录行数
     */
    int doRemove(Long id);

    /**
     * 获取记录数量
     *
     * @return 返回数据库记录数量
     */
    long getCount();

}
