package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TOrder)实体类
 *
 * @author liangjies
 * @since 2020-03-24 11:33:02
 */
public class TOrder implements Serializable {
    private static final long serialVersionUID = -81878035867987784L;
    
    private Integer id;
    /**
    * 订单号
    */
    private String orderid;
    /**
    * 用户id
    */
    private Integer userid;
    /**
    * 邮箱
    */
    private String email;
    /**
    * QQ号码
    */
    private String qq;
    /**
    * 产品id
    */
    private Integer pid;
    /**
    * 订单名称
    */
    private String productname;
    /**
    * 单价
    */
    private Double price;
    /**
    * 数量
    */
    private Integer number;
    /**
    * 订单金额
    */
    private Double money;
    /**
    * 查询密码
    */
    private String chapwd;
    /**
    * ip
    */
    private String ip;
    /**
    * 状态0待支付,1待处理,2已完成,3处理失败,-1删除
    */
    private Integer status;
    /**
    * 下单时间
    */
    private Integer addtime;
    /**
    * 支付时间
    */
    private Integer paytime;
    /**
    * 外部订单id
    */
    private String tradeid;
    /**
    * 支付渠道
    */
    private String paymethod;
    /**
    * 支付总金额
    */
    private Double paymoney;
    /**
    * 卡密
    */
    private String kami;
    /**
    * 额外配置1
    */
    private String configure1;
    /**
    * 备注
    */
    private String addons;
    /**
    * 0未删除,1已删除
    */
    private Integer isdelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getChapwd() {
        return chapwd;
    }

    public void setChapwd(String chapwd) {
        this.chapwd = chapwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getPaytime() {
        return paytime;
    }

    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public Double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(Double paymoney) {
        this.paymoney = paymoney;
    }

    public String getKami() {
        return kami;
    }

    public void setKami(String kami) {
        this.kami = kami;
    }

    public String getConfigure1() {
        return configure1;
    }

    public void setConfigure1(String configure1) {
        this.configure1 = configure1;
    }

    public String getAddons() {
        return addons;
    }

    public void setAddons(String addons) {
        this.addons = addons;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

}