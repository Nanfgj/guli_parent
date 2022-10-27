package com.nfgj.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Course;
import com.nfgj.edu.pojo.Teacher;
import com.nfgj.edu.service.CourseService;
import com.nfgj.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nanfgj
 * @create 2022-09-27 15:27
 */
@EnableCaching
@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //查询前8条热门课程，查询前4位名师
    @Cacheable(key = "'index'",value = "courseAndTeacher")
    @GetMapping("/index")
    public R index(){

        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");
        List<Course> courseList = courseService.list(courseWrapper);

        QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherWrapper);

        return R.ok().data("eduList",courseList).data("teacherList",teacherList);
    }
}
