package com.nfgj.edu.mapper;

import com.nfgj.edu.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nfgj.edu.pojo.vo.CoursePublishVo;


/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
public interface CourseMapper extends BaseMapper<Course> {

    //根据课程id查询出课程的所有信息
    CoursePublishVo getPublishCourseInfo(String courseId);
}
