package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TProducts;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TProducts)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
public interface TProductsDao {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TProducts> queryAllData();

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TProducts> queryActiveData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProducts queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProducts> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tProducts 实例对象
     * @return 对象列表
     */
    List<TProducts> queryAll(TProducts tProducts);

    /**
     * 新增数据
     *
     * @param tProducts 实例对象
     * @return 影响行数
     */
    int insert(TProducts tProducts);

    /**
     * 修改数据
     *
     * @param tProducts 实例对象
     * @return 影响行数
     */
    int update(TProducts tProducts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TProducts> queryByTid(@Param("tid") Integer tid);
}