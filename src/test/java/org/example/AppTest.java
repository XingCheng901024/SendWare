package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        {
            JFrame window = new JFrame();
            // JFrame中不要直接添加组件，先添加画板，Jpanel ，在Jpanel中添加组件
            // window中可以添加panel ，可添加多个
            JPanel panel = new JPanel();
            window.add(panel);
// 按钮
            JButton button1 = new JButton("b1");
            panel.add(button1);


            //单行文本框
            JTextField textfield = new JTextField(10);
            panel.add(textfield);

            // 多行文本域
            JTextArea textarea = new JTextArea(10, 10);
            panel.add(textarea);

            // 密码框
            JPasswordField passwordfield = new JPasswordField(10);
            panel.add(passwordfield);

            // 文本标签
            JLabel lable = new JLabel("cnm");
            panel.add(lable);

            // 复选按钮
            JCheckBox checkbox = new JCheckBox();
            panel.add(checkbox);

            // 单选按钮
            JRadioButton radiobox1 = new JRadioButton();
            JRadioButton radiobox2 = new JRadioButton();
            panel.add(radiobox1);
            //单选按钮要分组设定单选，
            ButtonGroup btnGroup = new ButtonGroup();
            btnGroup.add(radiobox1);
            btnGroup.add(radiobox2);

            //window的属性设置应该在所有的组件添加完成之后。
            // 设置窗口大小
            window.setSize(500, 400);

            //需要多大的窗口，自适应
            window.pack();

            // 设置窗口可见
            window.setVisible(true);
            //释放方法，就是关闭这个窗口
            //window.dispose();

            // 设置窗口大小固定
            window.setResizable(false);
            // 屏幕居中
            window.setLocationRelativeTo(null);
            // 加标题
            window.setTitle("华夏银行前置机报文发送工具");
            // 默认关闭选项 EXIT_ON_CLOSE // DO_NOTHING_ON_CLOSE
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // 去窗口默认主题
            //window.setUndecorated(true);

            //按钮的
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("华夏银行前置机报文发送工具");
                    Dialog dia = new JDialog(window,"请选择文件");
                    dia.setSize(200, 200);
                    dia.setVisible(true);
                }
            });
            //窗口的
            window.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("guanbima?");
                }
            });


        }
    }
}
