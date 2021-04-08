package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TAdminUserDao;
import cn.liangjies.faka.entity.TAdminUser;
import cn.liangjies.faka.service.TAdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TAdminUser)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-22 11:50:49
 */
@Service("tAdminUserService")
public class TAdminUserServiceImpl implements TAdminUserService {
    @Resource
    private TAdminUserDao tAdminUserDao;
    /**
     * 通过email查询单条数据
     *
     * @param email 主键
     * @return 实例对象
     */
    @Override
    public TAdminUser queryByEmail(String email){
        return this.tAdminUserDao.queryByEmail(email);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TAdminUser queryById(Integer id) {
        return this.tAdminUserDao.queryById(id);
    }


    /**
     * 修改密码
     *
     * @param password 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean updatePass(String password){
        return this.tAdminUserDao.updatePass(password) > 0;
    }

    /**
     * 验证管理员密码是否正正确
     *
     * @param password 密码MD5
     * @return 是否
     */
    @Override
    public  Boolean validPass(String password){
        return this.tAdminUserDao.validPass(password) > 0;
    }
}