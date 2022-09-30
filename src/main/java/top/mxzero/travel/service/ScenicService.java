package top.mxzero.travel.service;

import top.mxzero.travel.vo.Scenic;

import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
public interface ScenicService {
    Scenic get(Integer id);

    List<Scenic> getIsRecommended();

    List<Scenic> getIsHot();

    List<Scenic> getByAreaIdAndRecommended(Integer areaId);

    List<Scenic> list();

    Map<String, Object> split(int page, int size);

    /**
     * 根据地区ID以及星级分页获取数据
     *
     * @param page   当前页
     * @param size   每页大小
     * @param areaId 地区ID
     * @param star   星级
     * @return 以Map形式返回数据
     * key = currentPage 当前页
     * key = totalPage 总页数
     * key = size 当前页数据大小
     * key = data 当前页数据
     */
    Map<String, Object> splitByAreaIdAndStar(int page, int size, Integer areaId, Integer star);

    boolean save(Scenic scenic);

    boolean update(Scenic scenic);

    boolean remove(Integer id);
}
