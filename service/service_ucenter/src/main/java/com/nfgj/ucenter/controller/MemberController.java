package com.nfgj.ucenter.controller;


import com.nfgj.commonutils.JwtUtils;
import com.nfgj.commonutils.R;
import com.nfgj.ucenter.pojo.Member;
import com.nfgj.ucenter.pojo.vo.RegisterVo;
import com.nfgj.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-28
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    //用户登录
    @PostMapping("/login")
    public R loginUser(@RequestBody Member member){
        //member对象封装手机号和密码
        //调用service方法实现登录，返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //用户注册
    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用jwt工具类的方法，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

}

