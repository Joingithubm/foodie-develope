package com.athome.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.csvreader.CsvWriter;

/**
 * @Description:
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 3:59 下午
 */
public abstract class BaseExportService implements IExportService {


    private ExcelWriter excelWriter;
    private WriteSheet writeSheet;
    private CsvWriter csvWriter;

    @Override
    public void close(){
        try{
            if (excelWriter != null){
                excelWriter.finish();
            }
            if (csvWriter != null){
                csvWriter.close();
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    public ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(ExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
    }

    public WriteSheet getWriteSheet() {
        return writeSheet;
    }

    public void setWriteSheet(WriteSheet writeSheet) {
        this.writeSheet = writeSheet;
    }

    public CsvWriter getCsvWriter() {
        return csvWriter;
    }

    public void setCsvWriter(CsvWriter csvWriter) {
        this.csvWriter = csvWriter;
    }
}
