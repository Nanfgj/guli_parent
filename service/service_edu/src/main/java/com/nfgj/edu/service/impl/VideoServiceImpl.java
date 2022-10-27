package com.nfgj.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.edu.nacos.VodClient;
import com.nfgj.edu.pojo.Video;
import com.nfgj.edu.mapper.VideoMapper;
import com.nfgj.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;

    //根据课程id删除小节 和视频
    @Override
    public void removeVideoByCourseId(String courseId) {

        //根据课程id查询出小节
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        //根据小节id，查询出所有视频id
        videoQueryWrapper.select("video_source_id");
        List<Video> videoList = baseMapper.selectList(videoQueryWrapper);

        //List<Video>变成List<String>
        ArrayList<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                //放到videoList集合里面
                videoIds.add(videoSourceId);
            }
        }
        //根据多个视频id删除多个视频
        if (videoIds.size() > 0){
            vodClient.deleteBatch(videoIds);
        }


        //删除课程
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);

    }
}
