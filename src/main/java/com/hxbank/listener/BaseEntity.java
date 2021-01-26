package com.hxbank.listener;

public class BaseEntity {

    public static String protol;
    public static String method = "post";
    public static String url;
    public static String type = "Separator";
    public static String filePathStr;
    public static String requestContext;

    public static String getHost(){
        if(!isBlank(url)){
            return url.split(":")[0];
        }else{
            System.out.println("url is blank!");
            return null;
        }
    }

    public static String getPort(){
        if(!isBlank(url)){
            return url.split(":")[1];
        }else{
            System.out.println("url is blank!");
            return null;
        }
    }

    public static boolean isFileBlank(){
        return isBlank(filePathStr);
    }

    protected static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

}
