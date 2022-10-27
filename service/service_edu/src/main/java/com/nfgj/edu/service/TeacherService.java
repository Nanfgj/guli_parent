package com.nfgj.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nfgj.edu.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-19
 */
public interface TeacherService extends IService<Teacher> {

    //1.分页查询讲师的方法
    Map<String, Object> getTeacherFrontList(Page<Teacher> teacherPage);
}
