package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TEmail;
import cn.liangjies.faka.entity.TEmailQueue;

import java.util.List;

public interface TEmailService {
    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TEmailQueue> queryAllData();

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TEmail> queryAll();

    /**
     * 修改数据
     *
     * @param tConfig 实例对象
     * @return 是否成功
     */
    boolean update(TEmail tConfig);

    /**
     * 新增数据
     *
     * @param tEmail 实例对象
     * @return 是否成功
     */
    boolean insert(TEmail tEmail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TEmail queryById(Integer id);
}
