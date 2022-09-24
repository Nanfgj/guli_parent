package com.nfgj.edu.service.impl;

import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Course;
import com.nfgj.edu.mapper.CourseMapper;
import com.nfgj.edu.pojo.CourseDescription;
import com.nfgj.edu.pojo.vo.CourseInfoVo;
import com.nfgj.edu.pojo.vo.CoursePublishVo;
import com.nfgj.edu.service.ChapterService;
import com.nfgj.edu.service.CourseDescriptionService;
import com.nfgj.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfgj.edu.service.VideoService;
import com.nfgj.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    //引入courseDescriptionService,对课程简介表（Description）进行数据库操作
    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    //返回添加之后课程的id，为了方面方便添加大纲使用
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //从courseInfoVo中取出course的信息，然后通过数据库操作，将信息存入course表中
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = baseMapper.insert(course);
        if (insert <= 0){
            throw new GuliException(20001,"课程信息添加失败~");
        }
        String cid = course.getId(); //获取刚刚添加到课程表中的id，准备传给课程简介表

        //将courseInfo中，课程简介的信息存入Description表中
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        boolean save = courseDescriptionService.save(courseDescription);
        if (!save){
            throw new GuliException(20001,"课程信息添加失败~");
        }

        return cid;
    }

    //根据课程id，查询课程信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        //先查询出course的信息
        Course course = baseMapper.selectById(courseId);
        //封装课程信息到courseInfo中
        BeanUtils.copyProperties(course,courseInfoVo);


        //在查询出课程简介的信息
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        //封装课程简介到courseInfo中
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }


    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        //从courseInfo中取出course的信息进行修改
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int update = baseMapper.updateById(course);
        if (update == 0){
            throw new GuliException(2001,"课程信息修改失败~");
        }

        //从courseInfo中取出courseDescription的信息进行修改
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    //删除课程
    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);

        //根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //根据课程id删除课程简介
        courseDescriptionService.removeById(courseId);

        //根据课程id删除课程
        int deleteById = baseMapper.deleteById(courseId);
        if (deleteById == 0){
            throw new GuliException(2001,"课程删除失败~");
        }
    }

}
