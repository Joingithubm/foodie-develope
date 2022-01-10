package com.athome.service.impl;

import com.athome.service.BaseExportService;

import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 4:42 下午
 */
public class CsvExportServiceImpl extends BaseExportService {
    @Override
    public void export(List data) {
        try {
            List<List<String>> list = data;
            for (List<String> strings : list) {
                String[] objects1 = strings.toArray(strings.toArray(new String[0]));
                super.getCsvWriter().writeRecord(objects1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
