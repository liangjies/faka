package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TPayment)实体类
 *
 * @author liangjies
 * @since 2021-04-04 16:08:04
 */
public class TPayment implements Serializable {
    private static final long serialVersionUID = -13334688195550971L;

    private Integer id;
    /**
     * 支付名
     */
    private String payment;
    /**
     * 显示名称
     */
    private String payname;
    /**
     * 图片
     */
    private String payimage;
    /**
     * 别名
     */
    private String alias;

    private String signType;

    private String appId;

    private String appSecret;

    private String aliPublicKey;

    private String rsaPrivateKey;
    /**
     * 配置3
     */
    private String configure3;
    /**
     * 配置4
     */
    private String configure4;
    /**
     * 支付超时,0是不限制
     */
    private Integer overtime;
    /**
     * 0未激活,1已激活
     */
    private Integer active;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getPayimage() {
        return payimage;
    }

    public void setPayimage(String payimage) {
        this.payimage = payimage;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getConfigure3() {
        return configure3;
    }

    public void setConfigure3(String configure3) {
        this.configure3 = configure3;
    }

    public String getConfigure4() {
        return configure4;
    }

    public void setConfigure4(String configure4) {
        this.configure4 = configure4;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

}