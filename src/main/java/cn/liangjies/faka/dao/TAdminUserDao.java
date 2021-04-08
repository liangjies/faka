package cn.liangjies.faka.dao;

import cn.liangjies.faka.entity.TAdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * (TAdminUser)表数据库访问层
 *
 * @author liangjies
 * @since 2020-03-22 11:50:48
 */
public interface TAdminUserDao {
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
     * @return 影响行数
     */
    int updatePass(@Param("password") String password);

    /**
     * 验证管理员密码是否正正确
     *
     * @param password 密码MD5
     * @return 总行数
     */
    int validPass(@Param("password") String password);
}