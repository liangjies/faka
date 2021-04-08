package cn.liangjies.faka.entity.Dto;

import cn.liangjies.faka.entity.TProducts;
import lombok.Data;

import java.util.List;

@Data
public class GetListDto {
    int code;

    int length;

    List<TProducts> products;
}
