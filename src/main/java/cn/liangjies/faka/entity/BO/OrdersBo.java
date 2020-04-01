package cn.liangjies.faka.entity.BO;

import cn.liangjies.faka.entity.TOrder;
import lombok.Data;

import java.util.List;

@Data
public class OrdersBo {
    private static final long serialVersionUID = 1L;
    // 错误信息
    private String msg;
    // 状态码
    private Integer code;
    // 数据
    private List<TOrder> data;
    // 数据条数
    private long count;
}
