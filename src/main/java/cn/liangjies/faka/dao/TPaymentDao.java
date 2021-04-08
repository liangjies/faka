package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TPayment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TPayment)表数据库访问层
 *
 * @author liangjies
 * @since 2021-04-04 16:08:04
 */
public interface TPaymentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPayment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TPayment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tPayment 实例对象
     * @return 对象列表
     */
    List<TPayment> queryAll(TPayment tPayment);

    /**
     * 新增数据
     *
     * @param tPayment 实例对象
     * @return 影响行数
     */
    int insert(TPayment tPayment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TPayment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TPayment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TPayment> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TPayment> entities);

    /**
     * 修改数据
     *
     * @param tPayment 实例对象
     * @return 影响行数
     */
    int update(TPayment tPayment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TPayment> queryAllData();
}