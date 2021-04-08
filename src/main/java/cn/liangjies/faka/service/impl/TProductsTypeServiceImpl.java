package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TProductsTypeDao;
import cn.liangjies.faka.entity.TProductsType;
import cn.liangjies.faka.service.TProductsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TProductsType)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-24 10:56:49
 */
@Service("tProductsTypeService")
public class TProductsTypeServiceImpl implements TProductsTypeService {
    @Resource
    private TProductsTypeDao tProductsTypeDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TProductsType> queryAllData(){
        return this.tProductsTypeDao.queryAllData();
    }

    /**
     * 查询数据
     *
     * @return 实例对象
     */
    @Override
    public List<TProductsType> queryActiveData(){
        return this.tProductsTypeDao.queryActiveData();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProductsType queryById(Integer id) {
        return this.tProductsTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TProductsType> queryAllByLimit(int offset, int limit) {
        return this.tProductsTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tProductsType 实例对象
     * @return 实例对象
     */
    @Override
    public TProductsType insert(TProductsType tProductsType) {
        this.tProductsTypeDao.insert(tProductsType);
        return tProductsType;
    }

    /**
     * 修改数据
     *
     * @param tProductsType 实例对象
     * @return 实例对象
     */
    @Override
    public TProductsType update(TProductsType tProductsType) {
        this.tProductsTypeDao.update(tProductsType);
        return this.queryById(tProductsType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tProductsTypeDao.deleteById(id) > 0;
    }
}