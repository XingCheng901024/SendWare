package com.hxbank.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class HttpUtil {

    public static void send(){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=" + URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:12345/doGetControllerOne" + "?" + params);
        httpGet.setHeader("Content-Type", "application/json;charset=utf8");

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:12345/doGetControllerOne" + "?" + params);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        String filesKey = "files";
        File file1 = new File("C:\\Users\\JustryDeng\\Desktop\\back.jpg");
        try {
            multipartEntityBuilder.addBinaryBody(filesKey, file1, ContentType.DEFAULT_BINARY, URLEncoder.encode(file1.getName(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 其它参数(注:自定义contentType，设置UTF-8是为了防止服务端拿到的参数出现乱码)
        ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
        multipartEntityBuilder.addTextBody("name", "邓沙利文", contentType);
        multipartEntityBuilder.addTextBody("age", "25", contentType);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
