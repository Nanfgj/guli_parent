package com.nfgj.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.edu.pojo.Chapter;
import com.nfgj.edu.mapper.ChapterMapper;
import com.nfgj.edu.pojo.Video;
import com.nfgj.edu.pojo.chapter.ChapterVo;
import com.nfgj.edu.pojo.chapter.VideoVo;
import com.nfgj.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nfgj.edu.service.VideoService;
import com.nfgj.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-23
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    //引入VideoService，对video进行数据库操作
    @Autowired
    private VideoService videoService;

    //根据课程id查询课程的章节和小节
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //根据课程id查询所属课程的章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        List<ChapterVo> chapterVoList = new ArrayList<>();
        //遍历查询到的章节list，将每一个chapter封装到ChapterVo中
        for (int i = 0; i < chapterList.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            //将每一个chapter封装到ChapterVo中
            BeanUtils.copyProperties(chapterList.get(i),chapterVo);

            //根据当前的课程章节id和课程id，查询章节对应的小节
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("course_id",courseId);
            videoQueryWrapper.eq("chapter_id",chapterVo.getId());
            List<Video> videoList = videoService.list(videoQueryWrapper);

            ArrayList<VideoVo> videoVos = new ArrayList<>();
            //将查询到的小节信息封装到VideoVo中
            for (int m = 0; m < videoList.size(); m++) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(videoList.get(m),videoVo);
                videoVos.add(videoVo);
            }
            chapterVo.setChildren(videoVos);
            //将查询到的章节信息封装
            chapterVoList.add(chapterVo);
        }

        return chapterVoList;
    }

    //删除章节id，要判断章节中是否有小节
    @Override
    public boolean deleteChapter(String chapterId) {
        //先查询要删除的章节是否有小节
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(videoQueryWrapper);
        if (count > 0){
            throw new GuliException(2001,"该章节内还有小节，删除失败~");
        }else {
            //对章节进行删除
            int deleteById = baseMapper.deleteById(chapterId);

            return deleteById > 0;
        }
    }

    //根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        baseMapper.delete(chapterQueryWrapper);
    }
}
