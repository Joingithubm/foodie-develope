package com.athome.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:  商品评论详情VO
 * @Author Zengfc
 * @Date 2021/7/23 16:23
 * @Version 1.0
 */
@Data
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private Date createTime;
    private String specName;
}
