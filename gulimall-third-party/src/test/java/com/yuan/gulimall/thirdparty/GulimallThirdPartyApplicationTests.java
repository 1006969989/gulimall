package com.yuan.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.yuan.gulimall.thirdparty.component.SmsComponent;
import com.yuan.gulimall.thirdparty.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {
    @Autowired
    private OSSClient ossClient;
    @Autowired
    private SmsComponent smsComponent;

    @Test
    public void contextLoads() throws FileNotFoundException {
        String bucketName = "yuan-1006969989";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "test.png";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "C:\\Users\\yuan1\\Desktop\\19725845480.png";
        // 创建OSSClient实例。
        //OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new FileInputStream(filePath);
        // 创建PutObject请求。
        ossClient.putObject(bucketName, objectName, inputStream);
        //关闭OSSClient
        ossClient.shutdown();
        System.out.println("上传完成");

    }
    @Test
    public void sendMsm(){
        String host = "https://dfsmsv2.market.alicloudapi.com";
        String path = "/data/send_sms_v2";
        String method = "POST";
        String appcode = "f7cc7e6c4cc349dba9fff9a458405da0";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:1234");
        bodys.put("template_id", "TPL_0000");
        bodys.put("phone_number", "15035712388");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendMsm2(){
        smsComponent.sendCode("15035712388","10099");
    }
}
