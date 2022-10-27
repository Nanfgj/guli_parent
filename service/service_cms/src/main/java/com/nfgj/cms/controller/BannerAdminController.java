package com.nfgj.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nfgj.cms.pojo.CrmBanner;
import com.nfgj.cms.service.CrmBannerService;
import com.nfgj.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台banner管理接口
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-27
 */
@CrossOrigin
@RestController
@RequestMapping("/cmsservice/bannerAdmin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //分页查询banner
    @PostMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit){
        Page<CrmBanner> bannerPagePage = new Page<>(page,limit);
        crmBannerService.page(bannerPagePage,null);

        return R.ok().data("items",bannerPagePage.getRecords()).data("total",bannerPagePage.getTotal());
    }

    //添加banner
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.save(crmBanner);
        if (flag){
            return R.ok();
        }else return R.error();


    }


    //修改banner
    @PostMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.updateById(crmBanner);
        if (flag){
            return R.ok();
        }else return R.error();

    }

    //根据id删除banner
    @DeleteMapping("/deleteBannerById/{id}")
    public R deleteBannerById(@PathVariable String id){
        boolean flag = crmBannerService.removeById(id);
        if (flag){
            return R.ok();
        }else return R.error();
    }

    //根据id查询banner
    @GetMapping("/getBannerById/{id}")
    public R getBannerByid(@PathVariable String id){
        CrmBanner bannerById = crmBannerService.getById(id);
        return R.ok().data("item",bannerById);
    }

}

