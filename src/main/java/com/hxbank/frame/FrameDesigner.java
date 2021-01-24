package com.hxbank.frame;

import javax.swing.*;

public class FrameDesigner {

    public static JFrame frame = new JFrame("华夏银行前置机报文发送工具");
    public static JPanel protolPanel = new JPanel(); // 创建面板，这个类似于 HTML 的 div 标签
    public static JPanel urlPanel = new JPanel(); // 创建Url面板
    public static JPanel methodPanel = new JPanel(); // 创建method面板
    public static JPanel typePanel = new JPanel(); // 创建type面板
    public static JPanel filePanel = new JPanel(); // 创建选择文件面板
    public static JPanel requTitlePanel = new JPanel(); // 创建请求标题面板
    public static JPanel requContextPanel = new JPanel(); // 创建请求内容面板
    public static JPanel respTitlePanel = new JPanel(); // 创建响应标题面板
    public static JPanel respContextPanel = new JPanel(); // 创建响应内容面板
    public static JTextField urlText = new JTextField(20);
    public static JTextArea requTextArea = new JTextArea(20, 50);
    public static JTextField filePath = new JTextField(50);

    public String getUrl(){
        return urlText.getText();
    }

    public String getRequestContext(){
        return requTextArea.getText();
    }

    public static FrameDesigner getInstance(){
        return InnerClassHolder.instance;
    }

    private static class InnerClassHolder{
        private static FrameDesigner instance = new FrameDesigner();
    }

}
