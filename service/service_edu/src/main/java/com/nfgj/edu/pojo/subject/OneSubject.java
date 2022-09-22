package com.nfgj.edu.pojo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类的实例类，封装了二级分类
 * @author nanfgj
 * @create 2022-09-22 22:03
 */
@Data
public class OneSubject {
    private String id;

    private String title;

    //一个一级分类有多个二级分类
    List<TwoSubject> children = new ArrayList<>();
}
