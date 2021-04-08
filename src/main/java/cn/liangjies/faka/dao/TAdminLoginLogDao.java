package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TAdminLoginLog;

import java.util.List;

/**
 * 管理员登录日志(TAdminLoginLog)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-23 00:28:56
 */
public interface TAdminLoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    List<TAdminLoginLog> queryAll();

    /**
     * 新增数据
     *
     * @param tAdminLoginLog 实例对象
     * @return 影响行数
     */
    int insert(TAdminLoginLog tAdminLoginLog);

}