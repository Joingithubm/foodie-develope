package com.athome.service;

import java.util.List;

/**
 * @Description: 数据导出接口，支持 xlsx、xls、csv、txt。默认数据的第一行作为表头
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 3:34 下午
 */
public interface IExportService {
    /**
     * 支持累加
     * @param data 数据
     */
    void export( List data);

    void close();
}
