package com.nfgj.msm.service;

import java.util.Map;

/**
 * @author nanfgj
 * @create 2022-09-28 11:02
 */
public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}
