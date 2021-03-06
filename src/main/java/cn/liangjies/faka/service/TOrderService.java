package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TOrder;
import cn.liangjies.faka.entity.TReport;

import java.util.List;

/**
 * (TOrder)表服务接口
 *
 * @author liangjies
 * @since 2020-03-24 11:33:02
 */
public interface TOrderService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TOrder> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrder queryById(Integer id);

    /**
     * 通过订单ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    TOrder queryByOrderid(String oid);

    /**
     * 查询指定时间订单数量
     *
     * @param startTime,endTime
     * @return 数量
     */
    TReport queryByTime(int startTime, int endTime);

    /**
     * 查询多条数据
     *
     * @param qq,pwd
     * @return 对象列表
     */

    /**
     * 查询多条数据
     *
     * @param orderid
     * @return 对象列表
     */
    List<TOrder> queryAllOrderid(String orderid);

    /**
     * 查询多条数据
     *
     * @param qq,pwd
     * @return 对象列表
     */
    List<TOrder> queryByContact(String qq, String pwd);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 是否成功
     */
    boolean insert(TOrder tOrder);

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}