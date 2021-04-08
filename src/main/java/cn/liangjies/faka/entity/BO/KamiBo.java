package cn.liangjies.faka.entity.BO;

import lombok.Data;

@Data
public class KamiBo {
    private static final long serialVersionUID = 1L;
    // 错误信息
    private String msg;
    // 状态码
    private Integer code;
    //数据
    private String[] data;
}
