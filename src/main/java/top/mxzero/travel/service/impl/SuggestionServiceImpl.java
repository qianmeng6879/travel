package top.mxzero.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.travel.dao.SuggestionDao;
import top.mxzero.travel.service.SuggestionService;
import top.mxzero.travel.vo.Suggestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/27
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {
    @Autowired
    private SuggestionDao suggestionDao;

    @Override
    public Suggestion get(Long id) {
        return suggestionDao.findById(id);
    }

    @Override
    public List<Suggestion> list() {
        return suggestionDao.findAll();
    }

    @Override
    public Map<String, Object> split(int page, int size) {
        List<Suggestion> split = suggestionDao.findSplit((page - 1) * size, size);
        long count = suggestionDao.getCount();

        Map<String, Object> result = new HashMap<>();
        result.put("data", split);
        result.put("size", split.size());
        result.put("currentPage", page);
        result.put("totalPage", count / page);
        return result;
    }

    @Override
    public boolean save(Suggestion suggestion) {
        return suggestionDao.doCreate(suggestion) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return suggestionDao.doRemove(id) > 0;
    }

    @Override
    public long count() {
        return suggestionDao.getCount();
    }
}
