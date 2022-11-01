package top.mxzero.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.AreaDao;
import top.mxzero.travel.exception.ServiceException;
import top.mxzero.travel.service.AreaService;
import top.mxzero.travel.vo.Area;
import top.mxzero.travel.vo.Suggestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public Area get(Integer id) {
        return areaDao.findById(id);
    }

    @Override
    public Area getByName(String name) {
        return null;
    }

    @Override
    public List<Area> getIsRecommended(Integer size) {
        return areaDao.findIsRecommended();
    }

    @Override
    public Map<String, Object> split(int currentPage, int pageSize) {
        List<Area> split = areaDao.findSplit((currentPage - 1) * pageSize, pageSize);
        long count = areaDao.getCount();
        Map<String, Object> result = new HashMap<>();
        result.put("data", split);
        result.put("dataSize", split.size());
        result.put("currentPage", currentPage);
        result.put("pageSize", pageSize);
        result.put("totalPage", (count % pageSize != 0) ? count / pageSize + 1 : count / pageSize);
        return result;
    }

    @Override
    public List<Area> list() {
        return areaDao.findAll();
    }

    @Override
    public boolean save(Area area) {
        if (areaDao.findByName(area.getName()) != null) {
            throw new ServiceException(String.format("地区 %s 已存在", area.getName()));
        }
        return areaDao.doCreate(area) > 0;
    }

    @Override
    public boolean update(Area area) {
        return areaDao.doUpdate(area) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return areaDao.doRemove(id) > 0;
    }
}
