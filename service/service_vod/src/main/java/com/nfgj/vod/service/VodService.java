package com.nfgj.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author nanfgj
 * @create 2022-09-24 22:30
 */
public interface VodService {

    //将视频上传到阿里云
    String uploadVideoAly(MultipartFile file);

    //根据视频id删除阿里云视频
    void removeAlyVideo(String id);

    //删除课程时删除该课程的所有视频
    void removeMoreAlyVideo(List<String> videoList);
}
