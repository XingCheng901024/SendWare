package com.hxbank;

import com.hxbank.frame.FrameDesigner;
import com.hxbank.listener.*;

import javax.swing.*;
import java.awt.*;

import static com.hxbank.frame.FrameDesigner.*;

/**
 *
 */
public class App{
    private static FrameDesigner frameDesigner = FrameDesigner.getInstance();

    public static void main( String[] args ) {
        frameDesigner.frame.setLayout(new FlowLayout());
        placeProtolComponents();
        placeUrlComponents();
        // placeMethodComponents();
        placeSendComponents();
        placeTypeComponents();
        placeFileComponents();
        placeReqeustTitle();
        placeReqeustContent();
        placeResponseTitle();
        placeResponseContent();
        // 设置窗口大小
        frameDesigner.frame.setSize(1000, 900);
        frameDesigner.frame.setResizable(false);
        //需要多大的窗口，自适应
        //frame.pack();
        // 设置窗口可见
        frameDesigner.frame.setVisible(true);
        // 屏幕居中
        frameDesigner.frame.setLocationRelativeTo(null);
        // 默认关闭选项 EXIT_ON_CLOSE // DO_NOTHING_ON_CLOSE
        frameDesigner.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void placeProtolComponents() {
        RadioListener radioListener = new RadioListener(frameDesigner.urlPanel, frameDesigner.methodPanel);
        ButtonGroup protolGroup = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("-socket");
        JRadioButton radio2 = new JRadioButton("-http");
        protolGroup.add(radio1);
        protolGroup.add(radio2);
        radio1.addItemListener(radioListener);
        radio2.addItemListener(radioListener);

        frameDesigner.protolPanel.add(radio1);
        frameDesigner.protolPanel.add(radio2);
        frameDesigner.frame.add(frameDesigner.protolPanel,BorderLayout.CENTER);
    }

    private static void placeMethodComponents(){
        MethodListener methodListener = new MethodListener();
        JComboBox selectList = new JComboBox();
        selectList.addItem("get");
        selectList.addItem("post");
        selectList.addActionListener(methodListener);

        frameDesigner.methodPanel.add(selectList);
    }

    private static void placeUrlComponents(){
        JLabel userLabel = new JLabel("Url:");
        urlPanel.add(frameDesigner.methodPanel,BorderLayout.CENTER);
        urlPanel.add(userLabel);
        urlPanel.add(frameDesigner.urlText);
        frameDesigner.frame.add(urlPanel,BorderLayout.CENTER);
        frameDesigner.frame.add(typePanel,BorderLayout.CENTER);
    }

    private static void placeSendComponents(){
        // 按钮
        JButton send = new JButton("发送");
        send.addActionListener(new SendListener());
        urlPanel.add(send);
    }

    private static void placeTypeComponents(){
        TypeListener typeListener = new TypeListener();
        JComboBox selectList = new JComboBox();
        selectList.addItem("Xml");
        selectList.addItem("Separator");
        selectList.addActionListener(typeListener);
        typePanel.add(selectList);
    }

    private static void placeFileComponents(){
        JLabel fileSelect = new JLabel("选择文件:");

        JButton chooseFile = new JButton("...");
        chooseFile.addActionListener(new ChooseFileListener(frameDesigner.filePath));

        frameDesigner.filePanel.add(fileSelect);
        frameDesigner.filePanel.add(frameDesigner.filePath);
        frameDesigner.filePanel.add(chooseFile);
        frameDesigner.frame.add(frameDesigner.filePanel,BorderLayout.CENTER);
    }

    private static void placeReqeustTitle(){
        JLabel respTitle = new JLabel("                                               请求内容:                                               ");
        frameDesigner.requTitlePanel.add(respTitle);
        frameDesigner.frame.add(frameDesigner.requTitlePanel,BorderLayout.CENTER);
    }

    private static void placeReqeustContent(){
        // 多行文本域
        JScrollPane requScroll = new JScrollPane();
        requScroll.setBounds(28,209,752,265);
        frameDesigner.requContextPanel.add(requScroll);
        requScroll.setViewportView(requTextArea);
        frameDesigner.frame.add(frameDesigner.requContextPanel,BorderLayout.CENTER);
    }

    private static void placeResponseTitle(){
        JLabel respTitle = new JLabel("                                               响应内容:                                               ");
        frameDesigner.respTitlePanel.add(respTitle);
        frameDesigner.frame.add(frameDesigner.respTitlePanel,BorderLayout.CENTER);
    }

    private static void placeResponseContent(){
        // 多行文本域
        JScrollPane respScroll = new JScrollPane();
        respScroll.setBounds(28,209,752,265);
        frameDesigner.respContextPanel.add(respScroll);
        respScroll.setViewportView(respTextArea);
        frameDesigner.frame.add(frameDesigner.respContextPanel,BorderLayout.CENTER);
    }

}
