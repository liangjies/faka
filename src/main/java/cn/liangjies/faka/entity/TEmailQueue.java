package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TEmailQueue)实体类
 *
 * @author liangjies
 * @since 2020-03-24 11:37:54
 */
public class TEmailQueue implements Serializable {
    private static final long serialVersionUID = 296653112240598493L;
    
    private Integer id;
    /**
    *  收件人
    */
    private String email;
    /**
    * 标题
    */
    private String subject;
    /**
    * 内容
    */
    private String content;
    /**
    * 发送时间
    */
    private Integer addtime;
    /**
    * 发送时间
    */
    private Integer sendtime;
    /**
    * 发送错误
    */
    private String sendresult;
    /**
    * 0,未发送 ，1已发送，-1,失败
    */
    private Integer status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getSendtime() {
        return sendtime;
    }

    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendresult() {
        return sendresult;
    }

    public void setSendresult(String sendresult) {
        this.sendresult = sendresult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

}