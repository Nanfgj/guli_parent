package com.nfgj.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Teacher;
import com.nfgj.edu.pojo.vo.TeacherQuery;
import com.nfgj.edu.service.TeacherService;
import com.nfgj.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-19
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //1.查询所有讲师列表
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<Teacher> teacherList = teacherService.list(null);

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new GuliException(111225,"出现自定义异常");
        }

        return R.ok().data("teacherList",teacherList);
    }

    //2.根据id，删除讲师
    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacherById(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable String id){

        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //3.分页查询讲师列表
    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("{current}/{limit}")
    public R pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<Teacher> pageParam =new Page<>(current,limit);
        teacherService.page(pageParam,null);   //将分页后的所有数据都封装在pageParam中

        List<Teacher> records = pageParam.getRecords(); //从pageParam获取每页数据list集合
        long total = pageParam.getTotal(); //从pageParam获取分页总记录数

        return R.ok().data("total",total).data("rows",records);
    }

    //4.条件查询带分页
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<Teacher> pageParam = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        //获取传递过来的查询参数
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //根据查询条件构建条件查询
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }

        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件查询分页
        teacherService.page(pageParam,wrapper);
        List<Teacher> records = pageParam.getRecords(); //从pageParam获取每页数据list集合
        long total = pageParam.getTotal(); //从pageParam获取分页总记录数

        return R.ok().data("total",total).data("rows",records);
    }

    //5. 添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //6. 根据讲师id进行查询
    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    //7. 讲师修改功能
    @ApiOperation(value = "讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

