package cn.liangjies.faka.entity;

import lombok.Data;

@Data
public class Alipay {
    private static final long serialVersionUID = 1L;

    private int type;

    private int subjump;

    /**
    * 二维码地址
    */
    private String subjumpurl;
    /**
     * 支付方式
     */
    private String paymethod;
    /**
     * 二维码地址
     */
    private String qr;
    /**
     * 支付名称
     */
    private String payname;

    private int overtime;

    /**
     * 金额
     */
    private double money;

}
