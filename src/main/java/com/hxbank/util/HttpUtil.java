package com.hxbank.util;

public class HttpUtil {

    public static void send(){

        /*RestTemplate restTemplate = new RestTemplate();

        File localFile = new File("D:\\httptest.txt");
        // File localFile1 = new File("D:\\httptest1.txt");
        String url = "http://10.136.2.10:8080/yld-base-webapp-modules-appconnectorin/api/test.do";
        PostMethod filePost = new PostMethod(url);
        FilePart fp = new FilePart("file", localFile);
        FilePart fp1 = new FilePart("file1", localFile1);
        // StringPart:普通的文本参数
        StringPart uname = new StringPart("username", "aa");
        StringPart pass = new StringPart("password", "123456");
        Part[] parts = { uname, pass, fp,fp1 };
        // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
        MultipartRequestEntity mre = new MultipartRequestEntity(parts, filePost.getParams());
        filePost.setRequestEntity(mre);
        HttpClient client = new HttpClient();
        int status = client.executeMethod(filePost);
        System.out.println(status + "--------------");*/
    }

}
