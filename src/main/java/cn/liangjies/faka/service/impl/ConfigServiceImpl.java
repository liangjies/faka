package cn.liangjies.faka.service.impl;

import cn.liangjies.faka.entity.TAdminUser;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TAdminUserService;
import cn.liangjies.faka.service.TConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据配置
 *
 * @author liangjies
 * @since 2020-03-22 23:25:16
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Resource
    TConfigService tConfigService;

    @Resource
    TAdminUserService tAdminUserService;
    /**
     * 查询config数据
     *
     * @return Map对象
     */
    @Override
    public Map getConfig(){
        List<Map> listOfMap = tConfigService.queryConfig();
        Map<Object,Object> config = new HashMap<Object,Object>();
        for(int i = 0; i < listOfMap.size(); i++) {
            Object key = (Object) listOfMap.get(i).get("name");
            Object value = (Object)listOfMap.get(i).get("value");
            config.put(key, value);
        }
        return config;
    }

    /**
     * 查询管理员邮箱数据
     *
     * @return 字符串
     */
    @Override
    public String getAdminEmail(){
        TAdminUser tAdminUser = tAdminUserService.queryById(1);
        return tAdminUser.getEmail();
    }

    /**
     * 获取版本信息
     *
     * @return 字符串
     */
    @Override
    public String getVersion(){
        return "0.0.1";
    }
}
