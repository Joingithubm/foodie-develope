package com.athome.support;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.athome.enums.ExportCharsetType;
import com.athome.enums.ExportFormatType;
import com.athome.service.IExportService;
import com.athome.service.impl.CsvExportServiceImpl;
import com.athome.service.impl.ExcelExportServiceImpl;
import com.csvreader.CsvWriter;
import org.springframework.lang.Nullable;

import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 4:24 下午
 */
public class ExportServiceFactory {

    public static IExportService createExcelService(@Nullable ExportCharsetType charsetType, ExportFormatType formatType
                                                    , OutputStream outputStream){
        if (ExportFormatType.XLS == formatType || ExportFormatType.XLS == formatType){
            return iExportService(outputStream);
        }
        if (ExportFormatType.CSV == formatType){
            return csvExportService(outputStream, charsetType);
        }

        return null;
    }

    private static IExportService iExportService(OutputStream outputStream){
        ExcelExportServiceImpl exportService = new ExcelExportServiceImpl();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().sheetName("sheet").build();
        exportService.setExcelWriter(excelWriter);
        exportService.setWriteSheet(writeSheet);
        return exportService;
    }

    private static IExportService csvExportService(OutputStream outputStream,ExportCharsetType charsetType){
        CsvExportServiceImpl csvExportService = new CsvExportServiceImpl();
        CsvWriter csvWriter = new CsvWriter(outputStream,',', Charset.forName(charsetType.name));
        csvExportService.setCsvWriter(csvWriter);
        return csvExportService;
    }
}
