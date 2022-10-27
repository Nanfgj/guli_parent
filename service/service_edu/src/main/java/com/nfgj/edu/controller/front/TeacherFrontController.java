package com.nfgj.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Teacher;
import com.nfgj.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author nanfgj
 * @create 2022-09-30 17:24
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/front")
public class TeacherFrontController {
    @Autowired
    private TeacherService teacherService;

    //1.分页查询讲师的方法
    @GetMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable Long page, @PathVariable Long limit){

        Page<Teacher> teacherPage = new Page<Teacher>();
        Map<String,Object> teacherMap =  teacherService.getTeacherFrontList(teacherPage);

        //返回所有数据

        return R.ok().data(teacherMap);
    }
}
