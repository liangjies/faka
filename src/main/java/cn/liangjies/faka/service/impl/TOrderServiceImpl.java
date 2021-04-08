package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TOrderDao;
import cn.liangjies.faka.entity.TOrder;
import cn.liangjies.faka.entity.TReport;
import cn.liangjies.faka.service.TOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * (TOrder)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-24 11:33:02
 */
@Service("tOrderService")
public class TOrderServiceImpl implements TOrderService {
    @Resource
    private TOrderDao tOrderDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TOrder> queryAllData(){
        return this.tOrderDao.queryAllData();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TOrder queryById(Integer id) {
        return this.tOrderDao.queryById(id);
    }

    /**
     * 通过订单ID查询单条数据
     *
     * @param oid 主键
     * @return 实例对象
     */
    @Override
    public TOrder queryByOrderid(String oid){
        return this.tOrderDao.queryByOrderid(oid);
    }

    /**
     * 查询指定时间订单数量
     *
     * @param startTime,endTime
     * @param endTime
     * @return 数量
     */
    public TReport queryByTime(int startTime, int endTime){
        return this.tOrderDao.queryByTime(startTime, endTime);
    };


    /**
     * 查询多条数据
     *
     * @param orderid
     * @return 对象列表
     */
    @Override
    public List<TOrder> queryAllOrderid(String orderid){
        return this.tOrderDao.queryAllOrderid(orderid);
    }

    /**
     * 查询多条数据
     *
     * @param qq,pwd
     * @return 对象列表
     */
    @Override
    public List<TOrder> queryByContact(String qq, String pwd){
        return this.tOrderDao.queryByContact(qq, pwd);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TOrder> queryAllByLimit(int offset, int limit) {
        return this.tOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(TOrder tOrder) {
        return this.tOrderDao.insert(tOrder) > 0;
    }

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder update(TOrder tOrder) {
        this.tOrderDao.update(tOrder);
        return this.queryById(tOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tOrderDao.deleteById(id) > 0;
    }
}