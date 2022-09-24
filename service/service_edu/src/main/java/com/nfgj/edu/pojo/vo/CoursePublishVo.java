package com.nfgj.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

//课程信息最终发布实体类
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}