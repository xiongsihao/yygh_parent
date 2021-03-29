package com.xsh.easyExcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author : xsh
 * @create : 2021-03-29 - 22:18
 * @describe:
 */
public class testRead {

    public static void main(String[] args) {
        //读取文件路径
        String fileName = System.getProperty("user.dir")+"\\service\\service_cmn\\src\\test\\java\\com\\xsh\\excelFile\\01.xlsx";
        //调用方法实现读取操作  指定对应的监听器
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
