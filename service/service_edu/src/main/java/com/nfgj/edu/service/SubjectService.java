package com.nfgj.edu.service;

import com.nfgj.edu.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfgj.edu.pojo.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-22
 */
public interface SubjectService extends IService<Subject> {

    //添加课程分类
    void saveSubject(MultipartFile file, SubjectService subjectService);

    //课程分类列表(树形)
    List<OneSubject> getAllOneTwoSubject();
}
