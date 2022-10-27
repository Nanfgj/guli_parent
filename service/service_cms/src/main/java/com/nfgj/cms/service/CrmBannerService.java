package com.nfgj.cms.service;

import com.nfgj.cms.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author nanfgj
 * @since 2022-09-27
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有banner，
    List<CrmBanner> getAllBanner();
}
