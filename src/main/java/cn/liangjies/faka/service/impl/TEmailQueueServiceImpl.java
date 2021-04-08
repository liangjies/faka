package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.entity.TEmailQueue;
import cn.liangjies.faka.dao.TEmailQueueDao;
import cn.liangjies.faka.service.TEmailQueueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TEmailQueue)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-24 11:37:54
 */
@Service("tEmailQueueService")
public class TEmailQueueServiceImpl implements TEmailQueueService {
    @Resource
    private TEmailQueueDao tEmailQueueDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TEmailQueue> queryAllData(){
        return this.tEmailQueueDao.queryAllData();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TEmailQueue queryById(Integer id) {
        return this.tEmailQueueDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TEmailQueue> queryAllByLimit(int offset, int limit) {
        return this.tEmailQueueDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tEmailQueue 实例对象
     * @return 实例对象
     */
    @Override
    public TEmailQueue insert(TEmailQueue tEmailQueue) {
        this.tEmailQueueDao.insert(tEmailQueue);
        return tEmailQueue;
    }

    /**
     * 修改数据
     *
     * @param tEmailQueue 实例对象
     * @return 实例对象
     */
    @Override
    public TEmailQueue update(TEmailQueue tEmailQueue) {
        this.tEmailQueueDao.update(tEmailQueue);
        return this.queryById(tEmailQueue.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tEmailQueueDao.deleteById(id) > 0;
    }
}