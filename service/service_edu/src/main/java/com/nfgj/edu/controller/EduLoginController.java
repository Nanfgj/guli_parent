package com.nfgj.edu.controller;

import com.nfgj.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * 实现用户登录
 * @author nanfgj
 * @create 2022-09-21 9:34
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login
    @PostMapping("/login")
    public R login(){
        //提供前端需要的用户属性   token
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        //提供前端需要的用户属性   roles    name    avatar
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
