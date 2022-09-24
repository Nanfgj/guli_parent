package com.nfgj.edu.service;

import com.nfgj.edu.pojo.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nfgj.edu.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
public interface ChapterService extends IService<Chapter> {

    //根据课程id查询课程的章节和小节
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节id，要判断章节中是否有小节
    boolean deleteChapter(String chapterId);

    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
