package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TPaymentDao;
import cn.liangjies.faka.entity.TPayment;
import cn.liangjies.faka.service.TPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPayment)表服务实现类
 *
 * @author liangjies
 * @since 2021-04-04 16:08:04
 */
@Service("tPaymentService")
public class TPaymentServiceImpl implements TPaymentService {
    @Resource
    private TPaymentDao tPaymentDao;
    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TPayment> queryAllData(){
        return this.tPaymentDao.queryAllData();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TPayment queryById(Integer id) {
        return this.tPaymentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TPayment> queryAllByLimit(int offset, int limit) {
        return this.tPaymentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tPayment 实例对象
     * @return 实例对象
     */
    @Override
    public TPayment insert(TPayment tPayment) {
        this.tPaymentDao.insert(tPayment);
        return tPayment;
    }

    /**
     * 修改数据
     *
     * @param tPayment 实例对象
     * @return 实例对象
     */
    @Override
    public TPayment update(TPayment tPayment) {
        this.tPaymentDao.update(tPayment);
        return this.queryById(tPayment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tPaymentDao.deleteById(id) > 0;
    }
}