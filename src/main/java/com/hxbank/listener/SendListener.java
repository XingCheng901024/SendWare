package com.hxbank.listener;

import com.hxbank.frame.FrameDesigner;
import com.hxbank.util.SocketUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SendListener extends BaseListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("sending...");
        FrameDesigner frameDesigner = FrameDesigner.getInstance();
        System.out.println(protol);
        System.out.println(method);
        System.out.println(frameDesigner.getUrl());
        System.out.println(filePathStr);
        System.out.println(frameDesigner.getRequestContext());

        if("http".equals(protol)){

        }else if("socket".equals(protol)){
            try {
                SocketUtil.send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {}

    }

}
