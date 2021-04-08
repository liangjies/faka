package cn.liangjies.faka.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单数量
     */
    private int total;
    /**
     * 订单金额
     */
    private BigDecimal shouru = BigDecimal.valueOf(0);
}
