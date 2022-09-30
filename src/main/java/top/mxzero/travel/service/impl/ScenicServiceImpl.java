package top.mxzero.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.ScenicDao;
import top.mxzero.travel.service.ScenicService;
import top.mxzero.travel.vo.Scenic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
    private ScenicDao scenicDao;

    @Override
    public Scenic get(Integer id) {
        return scenicDao.findById(id);
    }

    @Override
    public List<Scenic> getIsRecommended() {
        return scenicDao.findIsRecommended();
    }

    @Override
    public List<Scenic> getIsHot() {
        return scenicDao.findIsHot();
    }

    @Override
    public List<Scenic> getByAreaIdAndRecommended(Integer areaId) {
        return scenicDao.findByAreaIdAndRecommended(areaId);
    }

    @Override
    public List<Scenic> list() {
        return scenicDao.findAll();
    }

    @Override
    public Map<String, Object> split(int page, int size) {

        List<Scenic> data = scenicDao.findSplit((page - 1) * size, size);
        int count = scenicDao.getCount();

        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("cp", page);
        map.put("size", data.size());
        map.put("total", count);
        if (count % size == 0) {
            map.put("totalPage", count / size);
        } else {
            map.put("totalPage", count / size + 1);
        }
        return map;
    }

    @Override
    public Map<String, Object> splitByAreaIdAndStar(int page, int size, Integer areaId, Integer star) {
        List<Scenic> data = scenicDao.findSplitByAreaIdAndStar((page - 1) * size, size, areaId, star);

        int count = scenicDao.getCountByAreaIdAndStar(areaId, star);

        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("size", data.size());
        result.put("currentPage", page);
        result.put("totalPage", count / size);

        return result;
    }

    @Override
    public boolean save(Scenic scenic) {
        return scenicDao.doCreate(scenic) > 0;
    }

    @Override
    public boolean update(Scenic scenic) {
        return scenicDao.doUpdate(scenic) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return scenicDao.doRemove(id) > 0;
    }
}
