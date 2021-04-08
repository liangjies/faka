package cn.liangjies.faka.entity.BO;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthBo  implements Serializable {
    private static final long serialVersionUID = 1L;
    // 错误信息
    private String msg;
    // 状态码
    private Integer code;
}
