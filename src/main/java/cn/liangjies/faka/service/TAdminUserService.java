package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TAdminUser;

/**
 * (TAdminUser)表服务接口
 *
 * @author liangjies
 * @since 2020-03-22 11:50:49
 */
public interface TAdminUserService {

    /**
     * 通过email查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    TAdminUser queryByEmail(String email);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAdminUser queryById(Integer id);

    /**
     * 修改密码
     *
     * @param password 实例对象
     * @return 是否成功
     */
    Boolean updatePass(String password);

    /**
     * 验证管理员密码是否正正确
     *
     * @param password 密码MD5
     * @return 是否成功
     */
    Boolean validPass(String password);
}