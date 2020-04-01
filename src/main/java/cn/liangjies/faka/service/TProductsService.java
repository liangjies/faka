package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TProducts;
import java.util.List;

/**
 * (TProducts)表服务接口
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
public interface TProductsService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TProducts> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProducts queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProducts> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tProducts 实例对象
     * @return 实例对象
     */
    TProducts insert(TProducts tProducts);

    /**
     * 修改数据
     *
     * @param tProducts 实例对象
     * @return 实例对象
     */
    TProducts update(TProducts tProducts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}