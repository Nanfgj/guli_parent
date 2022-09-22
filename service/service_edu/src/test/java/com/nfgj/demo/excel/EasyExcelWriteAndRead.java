package com.nfgj.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现excel写的操作
 * @author nanfgj
 * @create 2022-09-22 15:47
 */
public class EasyExcelWriteAndRead {
    public static void main(String[] args) {
        //1. 设置要写入文件夹地址和excel文件名称
        String fileName = "D:\\write.xlsx";
        //2. 调用easyexcel里面的方法实现写操作
        //write方法两个参数：文件路径名称  实体类.class
        //EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        //实现excel读的操作
        //              文件路径名称， 实体类     监听器对象
        EasyExcel.read(fileName,DemoData.class,new ReadWatch()).sheet().doRead();
    }

    public static List<DemoData> getData(){
        ArrayList<DemoData> demoData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("jack" + i);
            demoData.add(data);
        }
        return demoData;
    }
}
