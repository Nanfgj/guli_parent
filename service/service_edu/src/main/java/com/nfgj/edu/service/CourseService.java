package com.nfgj.edu.service;

import com.nfgj.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfgj.edu.pojo.vo.CourseInfoVo;
import com.nfgj.edu.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
public interface CourseService extends IService<Course> {

    //返回添加之后课程的id，为了方面方便添加大纲使用
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id，查询课程信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);
}
