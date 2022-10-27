package com.nfgj.vod.Utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 初始化操作，创建DefaultACSClient对象
 * @author nanfgj
 * @create 2022-09-24 21:26
 */
public class InitVodCilent {
    public static DefaultAcsClient initVodClient(String accessKeyId,String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai"; //点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
