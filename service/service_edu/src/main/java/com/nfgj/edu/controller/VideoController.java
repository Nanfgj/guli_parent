package com.nfgj.edu.controller;


import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Video;
import com.nfgj.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);
        return R.ok();
    }

    //删除小节
    //TODO 后面这个方法需要完善，删除小节的时候，同时把里面的视频删除
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        videoService.removeById(videoId);
        return R.ok();
    }

    //修改小节
    //根据小节id查询
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){

        Video video = videoService.getById(videoId);
        return R.ok().data("video",video);
    }

    //根据小节id修改
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody Video video){

        videoService.updateById(video);
        return R.ok();
    }

}

