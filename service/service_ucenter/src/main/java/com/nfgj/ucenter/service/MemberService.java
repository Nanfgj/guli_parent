package com.nfgj.ucenter.service;

import com.nfgj.ucenter.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfgj.ucenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-28
 */
public interface MemberService extends IService<Member> {

    //用户登录
    String login(Member member);

    //用户注册
    void register(RegisterVo registerVo);

    //判断数据表里面是否存在相同微信信息，根据openid判断
    Member getOpenIdMember(String openid);
}
