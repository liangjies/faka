package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TEmailQueue;
import java.util.List;

/**
 * (TEmailQueue)表服务接口
 *
 * @author liangjies
 * @since 2020-03-24 11:37:54
 */
public interface TEmailQueueService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TEmailQueue> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TEmailQueue queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TEmailQueue> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tEmailQueue 实例对象
     * @return 实例对象
     */
    TEmailQueue insert(TEmailQueue tEmailQueue);

    /**
     * 修改数据
     *
     * @param tEmailQueue 实例对象
     * @return 实例对象
     */
    TEmailQueue update(TEmailQueue tEmailQueue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}