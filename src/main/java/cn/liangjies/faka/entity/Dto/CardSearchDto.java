package cn.liangjies.faka.entity.Dto;

import lombok.Data;

@Data
public class CardSearchDto {

    Integer page=1;

    Integer limit=10;

    String card;

    Integer pid;

    Integer active;
}
