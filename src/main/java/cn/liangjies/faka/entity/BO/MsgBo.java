package cn.liangjies.faka.entity.BO;

import lombok.Data;

@Data
public class MsgBo {
    private static final long serialVersionUID = 1L;
    // 错误信息
    private String msg;
    // 状态码
    private Integer code;
}
