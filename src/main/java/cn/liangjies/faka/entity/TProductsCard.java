package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TProductsCard)实体类
 *
 * @author liangjies
 * @since 2020-03-24 11:14:15
 */
public class TProductsCard implements Serializable {
    private static final long serialVersionUID = 923076048079316690L;
    
    private Integer id;
    /**
    * 商品id
    */
    private Integer pid;
    /**
    * 卡密
    */
    private String card;
    /**
    * 添加时间
    */
    private Integer addtime;
    /**
    * 0可用 1已使用
    */
    private Integer active;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

}