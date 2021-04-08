package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TEmail;

import java.util.List;

/**
 * (TEmail)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-23 21:44:39
 */
public interface TEmailDao {
    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TEmail> queryAll();

    /**
     * 修改数据
     *
     * @param tEmail 实例对象
     * @return 影响行数
     */
    int update(TEmail tEmail);

    /**
     * 新增数据
     *
     * @param tEmail 实例对象
     * @return 影响行数
     */
    int insert(TEmail tEmail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TEmail queryById(Integer id);
}