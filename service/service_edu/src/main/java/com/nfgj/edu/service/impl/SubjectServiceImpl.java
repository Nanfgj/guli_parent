package com.nfgj.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.edu.listener.SubjectExcelListener;
import com.nfgj.edu.pojo.Subject;
import com.nfgj.edu.mapper.SubjectMapper;
import com.nfgj.edu.pojo.excel.SubjectData;
import com.nfgj.edu.pojo.subject.OneSubject;
import com.nfgj.edu.pojo.subject.TwoSubject;
import com.nfgj.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-22
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {


        try {
            //获取文件输入流
            InputStream inputStream = file.getInputStream();

            //调用方法读取excel
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询一级分类
        QueryWrapper<Subject> oneSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.eq("parent_id","0");
        List<Subject> oneSubjectList = baseMapper.selectList(oneSubjectWrapper);

        //查询二级分类
        QueryWrapper<Subject> twoSubjectWrapper = new QueryWrapper<>();
        twoSubjectWrapper.ne("parent_id","0");
        List<Subject> twoSubjectList = baseMapper.selectList(twoSubjectWrapper);

        //最终返回的一级二级分类封装好的list
        List<OneSubject> finallyOneAndTwoSubjectList = new ArrayList<>();

        //下面五个对象是为了节省栈空间
        Subject getOneSubject = null;
        OneSubject oneSubject = null;
        List<TwoSubject> twolist = null;
        Subject getTwoSubject = null;
        TwoSubject twoSubject = null;

        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) { //遍历刚刚查到的一级分类的list
            getOneSubject = oneSubjectList.get(i); // 得到oneSubjectList中具体的Subject
            oneSubject = new OneSubject();   //创建OneSubject的实例  准备获取Subject的值
            //oneSubject.setId(getOneSubject.getId());       //赋值
            //oneSubject.setTitle(getOneSubject.getTitle()); //赋值
            BeanUtils.copyProperties(getOneSubject,oneSubject);

            //封装二级分类到一级分类中，  条件是  二级分类的parent_id == 一级分类的id
            twolist = new ArrayList<>();  //准备封装符合条件的二级分类，然后添加到一级分类
            for (int m = 0; m < twoSubjectList.size(); m++) {
                getTwoSubject = twoSubjectList.get(m);  //得到twoSubjectList中具体的subject
                if (oneSubject.getId().equals(getTwoSubject.getParentId())){  //判断   符合的话准备赋值，然后封装
                    twoSubject = new TwoSubject();    //创建TwoSubject的实例  准备获取Subject的值
                    BeanUtils.copyProperties(getTwoSubject,twoSubject);  //赋值
                    twolist.add(twoSubject);    //封装，找到所有的符合条件的二级分类
                }
                oneSubject.setChildren(twolist);   //将符合条件的二级分类封装到一级分类
            }
            finallyOneAndTwoSubjectList.add(oneSubject); //将得到的一级分类封装到最终的list
        }

        return finallyOneAndTwoSubjectList;
    }
}
