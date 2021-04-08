package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TAdminUser)实体类
 *
 * @author liangjies
 * @since 2020-03-22 11:50:47
 */
public class TAdminUser implements Serializable {
    private static final long serialVersionUID = 931170128556153065L;
    
    private Integer id;
    
    private String email;
    
    private String password;
    
    private String secret;
    
    private Integer updatetime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

}