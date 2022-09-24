package com.nfgj.edu.controller;


import com.nfgj.commonutils.R;
import com.nfgj.edu.pojo.Chapter;
import com.nfgj.edu.pojo.chapter.ChapterVo;
import com.nfgj.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //根据课程id查询课程的章节和小节
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }

    //根据章节id进行查询
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){

        Chapter eduChapter = chapterService.getById(chapterId);

        return R.ok().data("chapter",eduChapter);
    }

    //根据章节id进行修改
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){

        chapterService.updateById(chapter);
        return R.ok();
    }

    //删除章节id，要判断章节中是否有小节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){

        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag){
            return R.ok();
        }else return R.error();
    }
}

