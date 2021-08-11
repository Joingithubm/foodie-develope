package com.athome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/7/23 16:55
 * @Version 1.0
 */
@RestController
public class BaseController {

    public final static Integer COMMENT_PAGE_NUM = 1;
    public final static Integer COMMENT_PAGE_SIZE = 10;
    public final static Integer SEARCH_ITEMS_PAGE_SIZE = 20;
}
