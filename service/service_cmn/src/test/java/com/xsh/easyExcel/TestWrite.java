package com.xsh.easyExcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xsh
 * @create : 2021-03-29 - 21:12
 * @describe: excel写测试案例
 */
public class TestWrite {

    public static void main(String[] args) {
        //excel文件目录+文件名
        String fileName = System.getProperty("user.dir")+"\\service\\service_cmn\\src\\test\\java\\com\\xsh\\excelFile\\01.xlsx";
        System.out.println(fileName);
        //构建写入excel内的数据
        List<UserData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            UserData data=new UserData();
            data.setUid(i);
            data.setUserName("xsh"+i);
            list.add(data);
        }

        //调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息").doWrite(list);
    }
}
