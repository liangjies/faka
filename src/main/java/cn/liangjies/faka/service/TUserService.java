package cn.liangjies.faka.service;

import cn.liangjies.faka.entity.TUser;

import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author liangjies
 * @since 2020-03-22 00:02:39
 */
public interface TUserService {
    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TUser> queryAllData();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}