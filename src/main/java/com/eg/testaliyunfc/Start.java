package com.eg.testaliyunfc;

import cn.hutool.http.HttpUtil;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.StreamRequestHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class Start implements StreamRequestHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {

//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
//        String bucketName = "my-bucket";

//        Credentials creds = context.getExecutionCredentials();
//        OSSClient client = new OSSClient(
//                endpoint, creds.getAccessKeyId(), creds.getAccessKeySecret(), creds.getSecurityToken());
//        client.putObject(bucketName, "my-object", new ByteArrayInputStream(new String("hello").getBytes()));
//        outputStream.write(new String("done").getBytes());

        System.out.println(HttpUtil.get("https://www.baidu.com/"));
    }

    public static void main(String[] args) {
        new Start().handleRequest(null,null,null);
    }
}