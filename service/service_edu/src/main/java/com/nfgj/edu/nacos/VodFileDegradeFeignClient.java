package com.nfgj.edu.nacos;

import com.nfgj.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nanfgj
 * @create 2022-09-26 21:02
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    //出错之后执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了~");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("删除多个视频出错了~");
    }
}
