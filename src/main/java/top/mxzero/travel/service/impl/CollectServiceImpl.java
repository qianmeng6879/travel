package top.mxzero.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.CollectDao;
import top.mxzero.travel.service.CollectService;
import top.mxzero.travel.vo.Collect;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public List<Collect> list(Long userId) {
        return collectDao.findAllByUserId(userId);
    }

    @Override
    public boolean isExists(Collect collect) {
        return collectDao.isExists(collect) > 0;
    }

    @Override
    public boolean save(Collect collect) {
        if (collectDao.isExists(collect) > 0) {
            return true;
        }
        return collectDao.doCreate(collect) > 0;
    }

    @Override
    public boolean remove(Collect collect) {
        return collectDao.doRemove(collect) > 0;
    }

    @Override
    public long count(Long userId) {
        return collectDao.getCount(userId);
    }
}
