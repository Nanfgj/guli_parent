package com.nfgj.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.commonutils.JwtUtils;
import com.nfgj.commonutils.MD5;
import com.nfgj.servicebase.handler.exceptionhandler.GuliException;
import com.nfgj.ucenter.pojo.Member;
import com.nfgj.ucenter.mapper.MemberMapper;
import com.nfgj.ucenter.pojo.vo.RegisterVo;
import com.nfgj.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-28
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;
    //用户登录
    @Override
    public String login(Member member) {
        //获取传递过来的手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //判断手机号和密码是否为空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败~");
        }

        //根据手机号查询数据库，看是否有用户
        QueryWrapper<Member> memberWrapper = new QueryWrapper<>();
        memberWrapper.eq("mobile",mobile);
        Member memberByMobile = baseMapper.selectOne(memberWrapper);
        //判断用户
        if (memberByMobile == null){
            throw new GuliException(20001,"用户不存在~");
        }

        //根据查询的用户信息，判断前台传递的密码是否正确
        //数据库中的密码使用MD5加密了，判断前需要给前端的密码加密
        if (!MD5.encrypt(password).equals(memberByMobile.getPassword())){
            throw new GuliException(20001,"密码错误~");
        }

        //判断当前用户是否被禁止登录
        if (memberByMobile.getIsDisabled()){
            throw new GuliException(20001,"登录失败，用户被禁用~");
        }

        //用户正常可以登录，使用jwt生成token返回
        String token = JwtUtils.getJwtToken(memberByMobile.getId(),memberByMobile.getNickname());

        return token;
    }

    //用户注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取用户注册信息
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)){
            throw new GuliException(20001,"注册失败~");
        }

        //查询手机号是否已注册
        QueryWrapper<Member> memberWrapper = new QueryWrapper<>();
        memberWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(memberWrapper);
        if (count > 0){
            throw new GuliException(20001,"手机号已注册~");
        }

        //判断短信验证码
        //获取
        String redisCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!redisCode.equals(code)){
            throw new GuliException(20001,"验证码错误~");
        }

        //完成用户注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setMobile(mobile);
        member.setIsDisabled(false);
        member.setAvatar("https://nfgj-picgo-images.oss-cn-shanghai.aliyuncs.com/images/202209271703967.jpg");

        baseMapper.insert(member);
    }

    //判断数据表里面是否存在相同微信信息，根据openid判断
    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(memberQueryWrapper);

        return member;
    }
}
