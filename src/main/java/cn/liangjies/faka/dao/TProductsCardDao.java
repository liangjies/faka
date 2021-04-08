package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.BO.CardType;
import cn.liangjies.faka.entity.Dto.CardSearchDto;
import cn.liangjies.faka.entity.TProductsCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TProductsCard)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-24 11:14:15
 */
public interface TProductsCardDao {
    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TProductsCard> queryAllData();


    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<CardType> queryCardAndType(CardSearchDto cardSearchDto);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProductsCard queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProductsCard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tProductsCard 实例对象
     * @return 对象列表
     */
    List<TProductsCard> queryAll(TProductsCard tProductsCard);

    /**
     * 新增数据
     *
     * @param tProductsCard 实例对象
     * @return 影响行数
     */
    int insert(TProductsCard tProductsCard);

    /**
     * 修改数据
     *
     * @param tProductsCard 实例对象
     * @return 影响行数
     */
    int update(TProductsCard tProductsCard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TProductsCard> buyCard(int pid, int num);

    int changeState(int id);
}