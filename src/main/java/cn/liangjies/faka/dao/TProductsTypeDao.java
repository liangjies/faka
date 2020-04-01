package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TProductsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TProductsType)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-24 10:56:49
 */
public interface TProductsTypeDao {
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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProductsType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tProductsType 实例对象
     * @return 对象列表
     */
    List<TProductsType> queryAll(TProductsType tProductsType);

    /**
     * 新增数据
     *
     * @param tProductsType 实例对象
     * @return 影响行数
     */
    int insert(TProductsType tProductsType);

    /**
     * 修改数据
     *
     * @param tProductsType 实例对象
     * @return 影响行数
     */
    int update(TProductsType tProductsType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}