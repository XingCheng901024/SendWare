package com.hxbank.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MethodListener extends BaseEntity implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox methodBox = (JComboBox) actionEvent.getSource();
        methodBox.getAction();
        method = methodBox.getSelectedItem().toString();
    }

}
