package cn.liangjies.faka.entity.BO;

import cn.liangjies.faka.entity.Alipay;
import lombok.Data;

@Data
public class PayBo {
    private static final long serialVersionUID = 1L;
    // 错误信息
    private String msg;
    // 状态码
    private Integer code;
    //数据
    private Alipay data;
}
