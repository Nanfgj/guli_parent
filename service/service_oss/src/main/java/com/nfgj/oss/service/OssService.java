package com.nfgj.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author nanfgj
 * @create 2022-09-22 10:05
 */
public interface OssService {
    //上传文件到oss
    String uploadFileAvatar(MultipartFile file);
}
