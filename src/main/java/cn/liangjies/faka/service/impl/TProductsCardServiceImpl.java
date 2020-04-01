package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TProductsCardDao;
import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.entity.TProductsCard;
import cn.liangjies.faka.service.TProductsCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TProductsCard)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-24 11:14:15
 */
@Service("tProductsCardService")
public class TProductsCardServiceImpl implements TProductsCardService {
    @Resource
    private TProductsCardDao tProductsCardDao;

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List <TProductsCard> queryAllData(){
        return this.tProductsCardDao.queryAllData();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProductsCard queryById(Integer id) {
        return this.tProductsCardDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TProductsCard> queryAllByLimit(int offset, int limit) {
        return this.tProductsCardDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tProductsCard 实例对象
     * @return 实例对象
     */
    @Override
    public TProductsCard insert(TProductsCard tProductsCard) {
        this.tProductsCardDao.insert(tProductsCard);
        return tProductsCard;
    }

    /**
     * 修改数据
     *
     * @param tProductsCard 实例对象
     * @return 实例对象
     */
    @Override
    public TProductsCard update(TProductsCard tProductsCard) {
        this.tProductsCardDao.update(tProductsCard);
        return this.queryById(tProductsCard.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tProductsCardDao.deleteById(id) > 0;
    }
}