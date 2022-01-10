package com.athome.enums;

/**
 * @Description: 导出自定义编码格式
 * @Author: fanchao@dtstack.com
 * @Date: 2022-01-07 10:54 上午
 */
public enum ExportCharsetType {
    /**
     * GBK 格式
     */
    GBK(0,"GBK"),
    /**
     * UTF-8 格式
     */
    UTF_8(1,"UTF-8");

    public final int type;
    public final String name;

    ExportCharsetType(int type,String name){
        this.type = type;
        this.name = name;
    }




    public static void main(String[] args) {
        System.out.println(ExportCharsetType.UTF_8.type);
    }
}
