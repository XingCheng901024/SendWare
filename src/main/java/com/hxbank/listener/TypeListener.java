package com.hxbank.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.hxbank.frame.FrameDesigner.filePanel;
import static com.hxbank.frame.FrameDesigner.frame;

public class TypeListener extends BaseEntity implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox methodBox = (JComboBox) actionEvent.getSource();
        methodBox.getAction();
        type = methodBox.getSelectedItem().toString();
        if("Separator".equals(type)){
            frame.remove(filePanel);
            frame.repaint();
        }else if("Xml".equals(type)){
            frame.add(filePanel, BorderLayout.CENTER);
            frame.repaint();
        }else{}
    }

}
