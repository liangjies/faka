package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * 基础配置(TConfig)实体类
 *
 * @author liangjies
 * @since 2020-03-20 11:46:52
 */
public class TConfig implements Serializable {
    private static final long serialVersionUID = 642095195030981979L;
    
    private Integer id;
    /**
    * 分类ID
    */
    private Integer catid;
    /**
    * 配置名
    */
    private String name;
    /**
    * 配置内容
    */
    private String value;
    /**
    * 备注
    */
    private String tag;
    /**
    * 最后修改时间
    */
    private Integer updatetime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

}