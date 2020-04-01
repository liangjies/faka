package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.entity.TConfig;
import cn.liangjies.faka.dao.TConfigDao;
import cn.liangjies.faka.service.TConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 基础配置(TConfig)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-20 11:46:53
 */
@Service("tConfigService")
public class TConfigServiceImpl implements TConfigService {
    @Resource
    private TConfigDao tConfigDao;

    /**
     * 通过name查询单条数据
     *
     * @return 实例对象
     */
    @Override
    public List<Map>  queryConfig(){
        return this.tConfigDao.queryConfig();
    };

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<TConfig> queryAll(){
        return this.tConfigDao.queryAll();
    }

    /**
     * 通过name查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    @Override
    public TConfig queryByname(String name) {
        return this.tConfigDao.queryByname(name);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TConfig queryById(Integer id) {
        return this.tConfigDao.queryById(id);
    }

    /**
     * 修改数据
     *
     * @param tConfig 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(TConfig tConfig) {
        return this.tConfigDao.update(tConfig) >0;
    }
}