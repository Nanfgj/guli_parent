package com.nfgj.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nfgj.edu.pojo.Teacher;
import com.nfgj.edu.mapper.TeacherMapper;
import com.nfgj.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    //1.分页查询讲师的方法
    @Override
    public Map<String, Object> getTeacherFrontList(Page<Teacher> teacherPage) {
        //排序
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage,wrapper);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total",teacherPage.getTotal());
        pageMap.put("records",teacherPage.getRecords());
        pageMap.put("current",teacherPage.getCurrent());
        pageMap.put("size",teacherPage.getSize());
        pageMap.put("pages",teacherPage.getPages());

        pageMap.put("hasNext",teacherPage.hasNext());
        pageMap.put("hasPrevious",teacherPage.hasPrevious());

        return pageMap;
    }
}
