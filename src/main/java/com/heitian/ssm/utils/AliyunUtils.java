package com.heitian.ssm.utils;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class AliyunUtils {

    private String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private String accessKeyId = "LTAI12CvFg3kIc8b";
    private String accessKeySecret = "UGa8vyj4LvSqmHdnv80VWFFiOznakG";
    private String bucketName = "yessdfsdfdfsd";
    private OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    public URL getUrl(String key) {
        Date expiration = new Date(new Date().getTime() + 360000 * 100000);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        // 关闭client
        ossClient.shutdown();
        return url;
    }

    public String upload(String fileName, File dir) {
        ossClient.putObject(bucketName, fileName, dir);
        // 关闭client
        ossClient.shutdown();
        return "success";
    }


}
