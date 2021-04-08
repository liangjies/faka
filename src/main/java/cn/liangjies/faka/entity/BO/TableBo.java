package cn.liangjies.faka.entity.BO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liangjies
 * @since 2020-03-21 22:15:52
 */
@Data
public class TableBo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 状态码
    private Integer code = 0;
    // 错误信息
    private String msg = "有数据";
    // 记录数
    private long count;
    // 内容
    private List data;
}
