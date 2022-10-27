package com.nfgj.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nfgj.cms.pojo.CrmBanner;
import com.nfgj.cms.mapper.CrmBannerMapper;
import com.nfgj.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-27
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner，
    @Override
    public List<CrmBanner> getAllBanner() {
        //根据id进行降序排列，显示排列之后前两条记录
        QueryWrapper<CrmBanner> bannerWrapper = new QueryWrapper<>();
        bannerWrapper.orderByDesc("id");
        bannerWrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(bannerWrapper);
        return list;
    }
}
