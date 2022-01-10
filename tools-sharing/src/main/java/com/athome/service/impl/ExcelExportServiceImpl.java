package com.athome.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.athome.service.BaseExportService;
import com.athome.service.IExportService;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.OutputStream;
import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 3:34 下午
 */
public class ExcelExportServiceImpl extends BaseExportService  {

    @Override
    public void export(List data) {
        super.getExcelWriter().write(data,super.getWriteSheet());
    }

}
