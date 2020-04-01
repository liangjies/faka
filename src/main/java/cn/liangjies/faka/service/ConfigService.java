package cn.liangjies.faka.service;

import java.util.Map;

/**
 * 数据配置
 *
 * @author liangjies
 * @since 2020-03-22 23:25:16
 */
public interface ConfigService {
    /**
     * 查询config数据
     *
     * @return Map对象
     */
    Map getConfig();

    /**
     * 查询管理员邮箱数据
     *
     * @return 字符串
     */
    String getAdminEmail();

    /**
     * 获取版本信息
     *
     * @return 字符串
     */
    String getVersion();

}
