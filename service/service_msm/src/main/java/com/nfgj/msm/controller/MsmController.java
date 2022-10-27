package com.nfgj.msm.controller;

import com.nfgj.commonutils.R;
import com.nfgj.msm.service.MsmService;
import com.nfgj.msm.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nanfgj
 * @create 2022-09-28 11:03
 */
@CrossOrigin
@RestController
@RequestMapping("/msmservice/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    //redis
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1.从redis获取验证码，如果获取到直接返回
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }


        //2 生成随机值，传递到阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        //用map封装然后传递
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);

        if (isSend){
            //发送成功，把发送的验证码存在redis中，并设置时间
            redisTemplate.opsForValue().set(phone,code,1000, TimeUnit.DAYS);
            return R.ok();
        }else {
            return R.ok().message("短信发送失败~");
        }

    }
}
