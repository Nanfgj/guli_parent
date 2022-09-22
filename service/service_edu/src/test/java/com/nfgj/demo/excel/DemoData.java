package com.nfgj.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author nanfgj
 * @create 2022-09-22 15:45
 */
@Data
public class DemoData {

    //设置excel表头名称
    @ExcelProperty(value = "学生名称",index = 0) //index  标记excel中对应的列
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
