package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TEmailQueue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TEmailQueue)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-24 11:37:54
 */
public interface TEmailQueueDao {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TEmailQueue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tEmailQueue 实例对象
     * @return 对象列表
     */
    List<TEmailQueue> queryAll(TEmailQueue tEmailQueue);

    /**
     * 新增数据
     *
     * @param tEmailQueue 实例对象
     * @return 影响行数
     */
    int insert(TEmailQueue tEmailQueue);

    /**
     * 修改数据
     *
     * @param tEmailQueue 实例对象
     * @return 影响行数
     */
    int update(TEmailQueue tEmailQueue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}