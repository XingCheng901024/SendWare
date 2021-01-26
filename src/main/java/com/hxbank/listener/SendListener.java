package com.hxbank.listener;

import com.hxbank.frame.FrameDesigner;
import com.hxbank.util.HttpUtil;
import com.hxbank.util.SocketUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.hxbank.frame.FrameDesigner.*;

public class SendListener extends BaseEntity implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        respTextArea.setText("");
        String t = filePathStr;
        String r = filePath.getText();
        filePathStr = filePath.getText();
        System.out.println("sending...");
        FrameDesigner frameDesigner = FrameDesigner.getInstance();
        url = frameDesigner.getUrl();
        requestContext = frameDesigner.getRequestContext();
        if("http".equals(protol)){
            HttpUtil.send();
        }else if("socket".equals(protol)){
            SocketUtil.send();
        }else {}
    }

}
