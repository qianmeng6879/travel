package top.mxzero.travel.service;

import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.vo.Area;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
public interface AreaService {
    /**
     * 根据地区ID获取地区信息
     *
     * @param id 地区ID
     * @return 以VO类型返回地区信息
     */
    Area get(Integer id);

    /**
     * 根据地区名称获取地区信息
     *
     * @param name 地区名称
     * @return 以VO类型返回地区信息
     */
    Area getByName(String name);

    /**
     * 获取推荐的地区信息
     *
     * @param size 获取的个数
     * @return 以List形式返回VO对象
     */
    List<Area> getIsRecommended(Integer size);

    List<Area> list();

    boolean save(Area area);

    boolean update(Area area);

    boolean remove(Integer id);
}
