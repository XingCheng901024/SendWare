package com.hxbank.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioListener extends BaseEntity implements ItemListener {
    private static JPanel urlPanel;
    private static JPanel methodPanel;

    public RadioListener(JPanel urlPanel, JPanel methodPanel){
        this.urlPanel = urlPanel;
        this.methodPanel = methodPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        JRadioButton jRadioButton = (JRadioButton)itemEvent.getSource();
        int state = itemEvent.getStateChange();
        String label = jRadioButton.getText();
        if (state == ItemEvent.SELECTED) {
            if("-socket".equals(label)){
                System.out.println("choose socket");
                super.protol = "socket";
                urlPanel.remove(methodPanel);
            }else if ("-http".equals(label)){
                System.out.println("choose http");
                super.protol = "http";
                urlPanel.add(methodPanel, BorderLayout.CENTER);
            }else {
                System.out.println("nothing");
            }
        }else {
            System.out.println(label + " is deselected...");
        }
        urlPanel.repaint();
    }

}
