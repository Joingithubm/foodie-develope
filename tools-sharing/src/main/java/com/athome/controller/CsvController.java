package com.athome.controller;

import com.athome.enums.ExportCharsetType;
import com.athome.enums.ExportFormatType;
import com.athome.service.IExportService;
import com.athome.support.ExportServiceFactory;
import com.csvreader.CsvWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-06 2:45 下午
 */
@RestController
@RequestMapping("/csv")
public class CsvController {

    @GetMapping("/downLoad")
    public void downLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String str = "csvTest";
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "filename=" + str + ".csv");

        List<String> head = Lists.newArrayList("姓名","性别","年龄");
        List<List<String>> headData = Lists.newArrayList();
        headData.add(head);
        List<String> row1 = Lists.newArrayList("zhang","man","13");
        List<String> row2 = Lists.newArrayList("lisi","man","13");
        List<String> row3 = Lists.newArrayList("wang","man","13");
        List<String> row4 = Lists.newArrayList("xiao","man","13");
        List<List<String>> data = Lists.newArrayList();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        ServletOutputStream outputStream = response.getOutputStream();
/*        CsvWriter csvWriter = new CsvWriter(outputStream,',', Charset.forName("GBK"));
        String[] objectString = new String[head.size()];
         String[] objects = head.toArray(objectString);

        csvWriter.writeRecord(objects);
        for (List<String> datum : data) {
            String[] objectString1 = new String[datum.size()];
            String[] objects1 = datum.toArray(objectString1);
            csvWriter.writeRecord(objects1);
        }


        csvWriter.close();*/

        IExportService excelService = ExportServiceFactory.createExcelService(ExportCharsetType.UTF_8, ExportFormatType.CSV, outputStream);
        excelService.export(headData);
        excelService.export(data);
        excelService.close();
        outputStream.close();
    }
}
