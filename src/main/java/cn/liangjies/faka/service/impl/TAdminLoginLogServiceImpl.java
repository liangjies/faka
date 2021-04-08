package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.dao.TAdminLoginLogDao;
import cn.liangjies.faka.entity.TAdminLoginLog;
import cn.liangjies.faka.service.TAdminLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员登录日志(TAdminLoginLog)表服务实现类
 *
 * @author liangjies
 * @since 2020-03-23 00:28:56
 */
@Service("tAdminLoginLogService")
public class TAdminLoginLogServiceImpl implements TAdminLoginLogService {
    @Resource
    private TAdminLoginLogDao tAdminLoginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @return 对象列表
     */
    @Override
    public List<TAdminLoginLog> queryAll(){
        return this.tAdminLoginLogDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param tAdminLoginLog 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(TAdminLoginLog tAdminLoginLog){
        return this.tAdminLoginLogDao.insert(tAdminLoginLog) > 0;
    }
}