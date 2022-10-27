package com.nfgj.cms.controller;


import com.nfgj.cms.pojo.CrmBanner;
import com.nfgj.cms.service.CrmBannerService;
import com.nfgj.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台banner管理接口
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-27
 */
@CrossOrigin
@RestController
@RequestMapping("/cmsservice/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有banner，
    @Cacheable(key = "'getAllBanner'",value = "banner")
    @GetMapping("/getAllBanner")
    public R getAll(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return R.ok().data("list",list);
    }

}

