package com.nfgj.edu.controller;


import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.subject.OneSubject;
import com.nfgj.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-22
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        //MultipartFile  获取上传的文件
        subjectService.saveSubject(file,subjectService);

        return R.ok();
    }

    //课程分类列表(树形)
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

