package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.dao.TProductsDao;
import cn.liangjies.faka.service.TProductsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TProducts)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
@Service("tProductsService")
public class TProductsServiceImpl implements TProductsService {
    @Resource
    private TProductsDao tProductsDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TProducts> queryAllData(){
        return this.tProductsDao.queryAllData();
    };

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProducts queryById(Integer id) {
        return this.tProductsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TProducts> queryAllByLimit(int offset, int limit) {
        return this.tProductsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tProducts 实例对象
     * @return 实例对象
     */
    @Override
    public TProducts insert(TProducts tProducts) {
        this.tProductsDao.insert(tProducts);
        return tProducts;
    }

    /**
     * 修改数据
     *
     * @param tProducts 实例对象
     * @return 实例对象
     */
    @Override
    public TProducts update(TProducts tProducts) {
        this.tProductsDao.update(tProducts);
        return this.queryById(tProducts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tProductsDao.deleteById(id) > 0;
    }
}