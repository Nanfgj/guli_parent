package com.nfgj.edu.pojo.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节实体类
 * @author nanfgj
 * @create 2022-09-23 21:57
 */
@Data
public class ChapterVo {

    private String id;
    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
