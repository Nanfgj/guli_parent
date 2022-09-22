package com.nfgj.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * excel读的监听器
 * @author nanfgj
 * @create 2022-09-22 16:04
 */
public class ReadWatch extends AnalysisEventListener<DemoData> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("*****" + data);
    }

    //读取表头内容
    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("表头内容：" + headMap);
    }

    //读取完之后执行的内容
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
