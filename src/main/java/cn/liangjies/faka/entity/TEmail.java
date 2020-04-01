package cn.liangjies.faka.entity;

import java.io.Serializable;

/**
 * (TEmail)实体类
 *
 * @author liangjies
 * @since 2020-03-23 21:21:38
 */
public class TEmail implements Serializable {
    private static final long serialVersionUID = -64539448302858776L;
    
    private Integer id;
    /**
    * 邮箱地址
    */
    private String mailaddress;
    /**
    * 邮箱密码
    */
    private String mailpassword;
    /**
    * 	发件人email
    */
    private String sendmail;
    /**
    * 发送人昵称
    */
    private String sendname;
    /**
    * 端口号
    */
    private String port;
    /**
    * 发送邮件服务端
    */
    private String host;
    /**
    * 0关，1开SMTP加密方式 0关，1ssl,2tls
    */
    private Integer smtpCrypto;
    /**
    * 0未激活 1激活
    */
    private Integer isactive;
    /**
    * 0未删除,1已删除
    */
    private Integer isdelete;
    /**
    * 邮件发送协议
    */
    private String protocol;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public String getMailpassword() {
        return mailpassword;
    }

    public void setMailpassword(String mailpassword) {
        this.mailpassword = mailpassword;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getSmtpCrypto() {
        return smtpCrypto;
    }

    public void setSmtpCrypto(Integer smtpCrypto) {
        this.smtpCrypto = smtpCrypto;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}