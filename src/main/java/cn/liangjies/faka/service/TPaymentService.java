package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TPayment;

import java.util.List;

/**
 * (TPayment)表服务接口
 *
 * @author liangjies
 * @since 2021-04-04 16:08:04
 */
public interface TPaymentService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TPayment> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPayment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TPayment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tPayment 实例对象
     * @return 实例对象
     */
    TPayment insert(TPayment tPayment);

    /**
     * 修改数据
     *
     * @param tPayment 实例对象
     * @return 实例对象
     */
    TPayment update(TPayment tPayment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}