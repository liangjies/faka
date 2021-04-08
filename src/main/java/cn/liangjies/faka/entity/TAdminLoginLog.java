package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * 管理员登录日志(TAdminLoginLog)实体类
 *
 * @author liangjies
 * @since 2020-03-23 00:28:56
 */
public class TAdminLoginLog implements Serializable {
    private static final long serialVersionUID = -94382024430710890L;
    
    private Integer id;
    /**
    * 管理员id
    */
    private Integer adminid;
    /**
    * 登录ip
    */
    private String ip;
    /**
    * 登录时间
    */
    private Integer addtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

}