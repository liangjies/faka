package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TAdminLoginLog;

import java.util.List;

/**
 * 管理员登录日志(TAdminLoginLog)表服务接口
 *
 * @author liangjies
 * @since 2020-03-23 00:28:56
 */
public interface TAdminLoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @return 对象列表
     */
    List<TAdminLoginLog> queryAll();

    /**
     * 新增数据
     *
     * @param tAdminLoginLog 实例对象
     * @return 是否成功
     */
    boolean insert(TAdminLoginLog tAdminLoginLog);
}