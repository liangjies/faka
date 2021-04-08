package cn.liangjies.faka.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TProducts)实体类
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
@Data
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


}