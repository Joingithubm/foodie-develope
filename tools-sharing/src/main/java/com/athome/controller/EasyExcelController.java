package com.athome.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.athome.enums.ExportCharsetType;
import com.athome.enums.ExportFormatType;
import com.athome.service.IExportService;
import com.athome.support.ExportServiceFactory;
import javafx.application.Application;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-05 8:13 下午
 */
@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @GetMapping("/downLoad")
    public void downLoadTest(HttpServletResponse response, HttpServletRequest request){

        try {
            String filenames = "111111";
//            String userAgent = request.getHeader("User-Agent");
//            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
//                filenames = URLEncoder.encode(filenames, "UTF-8");
//            } else {
//                filenames = new String(filenames.getBytes("UTF-8"), "ISO-8859-1");
//            }
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition", "filename=" + filenames + ".xls");
            ServletOutputStream outputStream = response.getOutputStream();
           // ExcelWriter writer = EasyExcel.write(outputStream).build();
            List<Object> data = new ArrayList<>();
            List<String> list = Lists.newArrayList("zhang","lisi","wangwu");
            List<String> list1 = Lists.newArrayList("zhang1","lisi1","wangwu1");
            data.add(list);
            data.add(list1);

            WriteSheet sheet = EasyExcel.writerSheet().sheetName("sheed").build();
            List<String> head = Lists.newArrayList("name","name1","name2");
            List<List<String>> headData = new ArrayList<>();
            headData.add(head);
            // head.forEach(h -> headData.add(Collections.singletonList(h)));
            //sheet.setHead(headData);
           // writer.write(data,sheet);

            //writer.finish();
            IExportService excelService = ExportServiceFactory.createExcelService(null, ExportFormatType.XLS, outputStream);




            excelService.export(headData);
            excelService.export(data);

            excelService.close();
            outputStream.close();

        } catch (Exception e) {
            System.out.println(e);
        }finally {

        }
    }


    public static void main(String[] args) throws IOException {
        List<Object> data = new ArrayList<>();
        List<String> list = Lists.newArrayList("zhang","lisi","wangwu");
        List<String> list1 = Lists.newArrayList("zhang1","lisi1","wangwu1");
        data.add(list);
        data.add(list1);

        WriteSheet sheet = EasyExcel.writerSheet().sheetName("sheed").build();
        List<String> head = Lists.newArrayList("name","name1","name2");
        data.add(0,head);

        System.out.println(data);

        FileOutputStream outputStream = new FileOutputStream("test.xlsx");
        IExportService excelService = ExportServiceFactory.createExcelService(null, ExportFormatType.XLS, outputStream);
        excelService.export(data);
        excelService.close();
        outputStream.close();


    }
}
