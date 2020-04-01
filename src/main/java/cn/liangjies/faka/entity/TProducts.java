package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TProducts)实体类
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
public class TProducts implements Serializable {
    private static final long serialVersionUID = -72797365287738605L;
    
    private Integer id;
    /**
    * 类型id
    */
    private Integer typeid;
    /**
    * 0未激活 1激活
    */
    private Integer active;
    /**
    * 产品名
    */
    private String name;
    /**
    * 密码
    */
    private String password;
    /**
    * 描述
    */
    private String description;
    /**
    * 0不控制,1控制
    */
    private Integer stockcontrol;
    /**
    * 数量
    */
    private Integer qty;
    /**
    * 虚拟库存
    */
    private Integer qtyVirtual;
    /**
    * 0关,1开
    */
    private Integer qtySwitch;
    /**
    * 销量
    */
    private Integer qtySell;
    
    private Double price;
    /**
    * 原价
    */
    private Double priceOri;
    /**
    * 0手动,1自动
    */
    private Integer auto;
    /**
    * 备注
    */
    private String addons;
    /**
    * 排序
    */
    private Integer sortNum;
    /**
    * 添加时间
    */
    private Integer addtime;
    /**
    * 0未删除,1已删除
    */
    private Integer isdelete;
    /**
    * 产品图片
    */
    private String imgurl;
    /**
    * 0无折扣,1有折扣
    */
    private Integer iszhekou;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStockcontrol() {
        return stockcontrol;
    }

    public void setStockcontrol(Integer stockcontrol) {
        this.stockcontrol = stockcontrol;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyVirtual() {
        return qtyVirtual;
    }

    public void setQtyVirtual(Integer qtyVirtual) {
        this.qtyVirtual = qtyVirtual;
    }

    public Integer getQtySwitch() {
        return qtySwitch;
    }

    public void setQtySwitch(Integer qtySwitch) {
        this.qtySwitch = qtySwitch;
    }

    public Integer getQtySell() {
        return qtySell;
    }

    public void setQtySell(Integer qtySell) {
        this.qtySell = qtySell;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceOri() {
        return priceOri;
    }

    public void setPriceOri(Double priceOri) {
        this.priceOri = priceOri;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public String getAddons() {
        return addons;
    }

    public void setAddons(String addons) {
        this.addons = addons;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getIszhekou() {
        return iszhekou;
    }

    public void setIszhekou(Integer iszhekou) {
        this.iszhekou = iszhekou;
    }

}