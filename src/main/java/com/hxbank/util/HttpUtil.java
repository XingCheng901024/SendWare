package com.hxbank.util;

import com.hxbank.frame.FrameDesigner;
import com.hxbank.listener.BaseEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.hxbank.frame.FrameDesigner.filePanel;
import static com.hxbank.frame.FrameDesigner.frame;

public class HttpUtil extends BaseEntity {

    private static FrameDesigner frameDesigner = FrameDesigner.getInstance();

    private static final String MESSAGE_END = "@@@@";

    public static void init(){
        System.out.println("http initing ...");
        System.out.println(protol);
        System.out.println(method);
        System.out.println(getHost());
        System.out.println(getPort());
        System.out.println(url);
        System.out.println(filePathStr);
        System.out.println(requestContext);

    }

    public static void send(){
        if("Separator".equals(type)){
            sendWithSeparator();
        }else if("Xml".equals(type)){
            try {
                sendWithXml();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
            }
        }else{}
    }

    public static void sendWithSeparator(){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        /*StringBuffer params = new StringBuffer();
        params.append("command=" + requestContext + MESSAGE_END);
        // 创建post请求
        HttpPost httpPost = new HttpPost(url+"?"+params);*/
        httpPost.setHeader("Content-Type", "text/html;charset=GBK");
        try {
            httpPost.setEntity(new ByteArrayEntity(new String("command=" + requestContext + MESSAGE_END).getBytes("GBK")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            frameDesigner.respTextArea.setText(e.getMessage());
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String respContext = EntityUtils.toString(responseEntity);
            System.out.println("响应内容为:" + respContext);
            frameDesigner.respTextArea.setText(respContext);
        } catch (IOException e) {
            e.printStackTrace();
            frameDesigner.respTextArea.setText(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            frameDesigner.respTextArea.setText(e.getMessage());
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
                frameDesigner.respTextArea.setText(e.getMessage());
            }
        }
    }

    public static void sendWithXml() throws UnsupportedEncodingException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        params.append("command=" + requestContext + MESSAGE_END);

        // 响应模型
        CloseableHttpResponse response = null;
        if("get".equals(method)){
            // 创建Get请求
            HttpGet httpGet = new HttpGet(url + "?" + params);
            httpGet.setHeader("Content-Type", "application/json;charset=GBK");
            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
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
                    frameDesigner.respTextArea.setText(e.getMessage());
                }
            }
        }else if("post".equals(method)){
            // 创建Post请求
            HttpPost httpPost = new HttpPost(url);
            File file = new File(filePathStr);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
            try {
                if(!isFileBlank()){
                    multipartEntityBuilder.addBinaryBody("file", new FileInputStream(file), ContentType.DEFAULT_BINARY, file.getName());
                }
                multipartEntityBuilder.addTextBody("command", requestContext + MESSAGE_END);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
            }
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);
            try {
                //response = httpClient.execute(httpPost);
                // 由客户端执行(发送)Get请求
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity responseEntity = httpResponse.getEntity();
                System.out.println("响应状态为:" + httpResponse.getStatusLine());
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    try {
                        String respContext = EntityUtils.toString(responseEntity);
                        System.out.println("响应内容为:" + respContext);
                        frameDesigner.respTextArea.setText(respContext);
                    } catch (IOException e) {
                        e.printStackTrace();
                        frameDesigner.respTextArea.setText(e.getMessage());
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                frameDesigner.respTextArea.setText(e.getMessage());
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
                    frameDesigner.respTextArea.setText(e.getMessage());
                }
            }
        }

        // 从响应模型中获取响应实体
        /*HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            try {
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

}
