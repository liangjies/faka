package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TUser)实体类
 *
 * @author liangjies
 * @since 2020-03-22 00:02:32
 */
public class TUser implements Serializable {
    private static final long serialVersionUID = -48366013083759701L;


    private Integer id;
    /**
    * 分组ID
    */
    private Integer groupid;
    /**
    * 用户名
    */
    private String nickname;
    /**
    * 密码
    */
    private String password;
    /**
    * 邮箱
    */
    private String email;
    /**
    * qq
    */
    private String qq;
    /**
    * 手机
    */
    private String mobilephone;
    /**
    * 余额
    */
    private Double money;
    /**
    * 积分
    */
    private Integer integral;
    /**
    * 用户自己的备注
    */
    private String tag;
    /**
    * 创建时间
    */
    private Integer createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

}