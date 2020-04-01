package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TEmailDao;
import cn.liangjies.faka.entity.TEmail;
import cn.liangjies.faka.entity.TEmailQueue;
import cn.liangjies.faka.service.TEmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("tEmailService")
public class TEmailServiceImpl implements TEmailService {
    @Resource
    private TEmailDao tEmailDao;

    @Override
    public List<TEmailQueue> queryAllData() {
        return null;
    }

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TEmail> queryAll(){
        return this.tEmailDao.queryAll();
    }

    /**
     * 修改数据
     *
     * @param tEmail 实例对象
     * @return 影响行数
     */
    @Override
    public boolean update(TEmail tEmail){
        return this.tEmailDao.update(tEmail) >0 ;
    }

    /**
     * 新增数据
     *
     * @param tEmail 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(TEmail tEmail){
        return this.tEmailDao.insert(tEmail) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id){
        return this.tEmailDao.deleteById(id) > 0;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TEmail queryById(Integer id){
        return this.tEmailDao.queryById(id);
    }
}
