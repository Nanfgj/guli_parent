package com.nfgj.edu.service;

import com.nfgj.edu.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
public interface VideoService extends IService<Video> {

    //根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
