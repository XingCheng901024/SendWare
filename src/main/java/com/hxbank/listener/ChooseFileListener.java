package com.hxbank.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseFileListener extends BaseEntity implements ActionListener {

    private static JTextField filePath;

    public ChooseFileListener(JTextField filePath){
        this.filePath = filePath;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("click...");
        JFileChooser jfc = new JFileChooser();// 文件选择器
        jfc.setCurrentDirectory(new File("/"));// 文件选择器的初始目录定为d盘
        // 绑定到选择文件，先择文件事件
        jfc.setFileSelectionMode(0);// 设定只能选择到文件
        int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
        if (state == 1) {
            return;// 撤销则返回
        } else {
            File f = jfc.getSelectedFile();// f为选择到的文件
            filePath.setText(f.getAbsolutePath());
            filePathStr = f.getAbsolutePath();
        }
    }

}
