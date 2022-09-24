package com.nfgj.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author nanfgj
 * @create 2022-09-24 22:30
 */
public interface VodService {

    //将视频上传到阿里云
    String uploadVideoAly(MultipartFile file);
}
