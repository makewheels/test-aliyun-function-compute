package com.eg.testaliyunfc;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.StreamRequestHandler;

import javax.servlet.annotation.HttpMethodConstraint;
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

        String json = "{\n" +
                "  \"version\": \"1\",\n" +
                "  \"missionName\": \"huobi\",\n" +
                "  \"target\": {\n" +
                "    \"type\": \"object-storage\",\n" +
                "    \"endpoint\": \"oss-cn-beijing.aliyuncs.com\",\n" +
                "    \"region\": \"cn-beijing\",\n" +
                "    \"bucketName\": \"web-spider\",\n" +
                "    \"path\": \"spider/${missionName}/${requestName}/${GMTString}.json\"\n" +
                "  },\n" +
                "  \"requests\": [\n" +
                "    {\n" +
                "      \"requestName\": \"sell-usdt-cny\",\n" +
                "      \"source\": {\n" +
                "        \"method\": \"GET\",\n" +
                "        \"url\": \"https://otc-api-hk.eiijo.cn/v1/data/trade-market?coinId=2&currency=1&tradeType=sell&currPage=1&payMethod=0&acceptOrder=-1&country=&blockType=general&online=1&range=0&amount=\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"requestName\": \"buy-usdt-cny\",\n" +
                "      \"source\": {\n" +
                "        \"method\": \"GET\",\n" +
                "        \"url\": \"https://otc-api-hk.eiijo.cn/v1/data/trade-market?coinId=2&currency=1&tradeType=buy&currPage=1&payMethod=0&acceptOrder=-1&country=&blockType=general&online=1&range=0&amount=\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray requests = jsonObject.getJSONArray("requests");
        for (int i = 0; i < requests.size(); i++) {
            JSONObject request = requests.getJSONObject(i);
            JSONObject sourceObject = request.getJSONObject("source");
            String url = sourceObject.getString("url");
            String method = sourceObject.getString("method");
            if (method.equals("GET")) {
                String response = HttpUtil.get(url);
                System.out.println(response);
            }

        }
    }

    public static void main(String[] args) {
        new Start().handleRequest(null, null, null);
    }
}