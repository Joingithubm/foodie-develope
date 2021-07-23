package com.athome.vo;

import lombok.Data;

/**
 * @Description: 用于展示商品评价等级vo
 * @Author Zengfc
 * @Date 2021/7/22 10:32
 * @Version 1.0
 */
@Data
public class CommentLevelCountsVo {

    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
