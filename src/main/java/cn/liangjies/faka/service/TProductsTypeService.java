package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TProductsType;

import java.util.List;

/**
 * (TProductsType)表服务接口
 *
 * @author liangjies
 * @since 2020-03-24 10:56:49
 */
public interface TProductsTypeService {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TProductsType> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProductsType queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProductsType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tProductsType 实例对象
     * @return 实例对象
     */
    TProductsType insert(TProductsType tProductsType);

    /**
     * 修改数据
     *
     * @param tProductsType 实例对象
     * @return 实例对象
     */
    TProductsType update(TProductsType tProductsType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}