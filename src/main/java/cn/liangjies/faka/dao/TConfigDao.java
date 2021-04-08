package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TConfig;

import java.util.List;
import java.util.Map;

/**
 * 基础配置(TConfig)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-20 11:46:52
 */
public interface TConfigDao {

    /**
     * 通过name查询单条数据
     *
     * @return 实例对象
     */
    List<Map>  queryConfig();

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TConfig> queryAll();

    /**
     * 通过name查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    TConfig queryByname(String name);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TConfig queryById(Integer id);


    /**
     * 修改数据
     *
     * @param tConfig 实例对象
     * @return 影响行数
     */
    int update(TConfig tConfig);

}