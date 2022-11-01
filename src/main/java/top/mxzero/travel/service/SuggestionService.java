package top.mxzero.travel.service;

import top.mxzero.travel.vo.Suggestion;

import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
public interface SuggestionService {
    /**
     * 根据ID获取建议详情
     *
     * @param id 主键值
     * @return 以VO对象返回
     */
    Suggestion get(Long id);

    /**
     * 获取全部建议信息
     *
     * @return 以List形式返回建议信息
     */
    List<Suggestion> list();

    /**
     * 分页获取建议信息
     *
     * @param page 当前页
     * @param size 每页大小
     * @return 以Map封装分页数据
     * key = currentPage 当前页
     * key = totalPage 总页数
     * key = pageSize 需要获取记录数量
     * key = size 当前记录数量
     * key = data 记录数据
     */
    Map<String, Object> split(int page, int size);

    /**
     * 新增建议信息
     *
     * @param suggestion 建议信息VO类型
     * @return 新增成功返回true，否则返回false
     */
    boolean save(Suggestion suggestion);

    /**
     * 根据ID删除建议信息
     *
     * @param id 主键值
     * @return 删除成功返回true，否则返回false
     */
    boolean remove(Long id);

    /**
     * 获取建议记录数量
     *
     * @return 记录总数量
     */
    long count();
}
