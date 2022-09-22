package com.nfgj.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.nfgj.oss.service.OssService;
import com.nfgj.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author nanfgj
 * @create 2022-09-22 10:06
 */
@Service
public class OssServiceImpl implements OssService {
    //上传文件到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();

            //获取上传的文件名
            String fileName = file.getOriginalFilename();

            //在图片名称里面加上随机唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-",""); //replaceAll，去除横杠
            fileName = uuid + fileName;

            //把把文件按照日期进行分类
            //获取当前日期
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            //拼接  2022/9/13/sdklf234521.jpg
            fileName = dataPath + "/" + fileName;

            // 创建PutObject请求。上传
            ossClient.putObject(bucketName, fileName, inputStream);

            //关闭ossClient
            ossClient.shutdown();

            //上传之后，需要返回oss的url  手动拼接
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
