package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TConfig;

import java.util.List;
import java.util.Map;

/**
 * 基础配置(TConfig)表服务接口
 *
 * @author liangjies
 * @since 2020-03-20 11:46:53
 */
public interface TConfigService {

    /**
     * 查询配置数据
     *
     * @return 实例对象
     */
    List<Map> queryConfig();

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TConfig> queryAll();

    /**
     * 通过ID查询单条数据
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
     * @return 是否成功
     */
    Boolean update(TConfig tConfig);
}