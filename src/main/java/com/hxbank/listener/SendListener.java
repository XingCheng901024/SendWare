package com.hxbank.listener;

import com.hxbank.frame.FrameDesigner;
import com.hxbank.util.HttpUtil;
import com.hxbank.util.SocketUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SendListener extends BaseEntity implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("sending...");
        FrameDesigner frameDesigner = FrameDesigner.getInstance();
        url = frameDesigner.getUrl();
        requestContext = frameDesigner.getRequestContext();

        if("http".equals(protol)){
            try {
                HttpUtil.send();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else if("socket".equals(protol)){
            try {
                SocketUtil.send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {}

    }

}
