package com.nfgj.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.edu.pojo.Subject;
import com.nfgj.edu.pojo.excel.SubjectData;
import com.nfgj.edu.service.SubjectService;
import com.nfgj.servicebase.handler.exceptionhandler.GuliException;

/**
 * 读取excel的监听器
 * @author nanfgj
 * @create 2022-09-22 17:25
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener 不能交给Spring进行管理，需要自己new，不能注入其他对象  这样就不能实现数据库操作
    //我们可以添加构造器方法，然后传入SubjectService对象。进行数据库操作
    private SubjectService subjectService;
    public SubjectExcelListener() {
    }
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行的读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //判断excel是否为空，为空不进行操作
        if (subjectData == null){
            throw new GuliException(20001,"文件数据为空~");
        }

        //一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        //判断一级分类是否重复
        Subject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject == null){  //oneSubject为空，表示数据库中没有读到的这个一级分类，要添加到数据库
            oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());  //一级分类名称
            subjectService.save(oneSubject);  //添加
        }
        String pid = oneSubject.getId();

        //判断二级分类是否重复
        Subject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoSubject == null){ //twoSubject为空，表示数据库中没有读到属于一级分类的这个二级分类，要添加到数据库
            twoSubject = new Subject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName()); //二级分类名称
            subjectService.save(twoSubject);  //添加
        }

    }

    //判断一级分类是否重复添加
    private Subject existOneSubject(SubjectService subjectService , String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类是否重复添加
    private Subject existTwoSubject(SubjectService subjectService , String name, String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }


    //读取后进行操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
