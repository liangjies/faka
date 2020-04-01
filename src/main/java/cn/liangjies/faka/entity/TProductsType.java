package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TProductsType)实体类
 *
 * @author liangjies
 * @since 2020-03-24 10:56:49
 */
public class TProductsType implements Serializable {
    private static final long serialVersionUID = -65752841774426214L;
    
    private Integer id;
    /**
    * 类型命名
    */
    private String name;
    /**
    * 分类描述
    */
    private String description;
    /**
    * 分类密码
    */
    private String password;
    /**
    * 排序
    */
    private Integer sortNum;
    /**
    * 0未激活,1已激活
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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