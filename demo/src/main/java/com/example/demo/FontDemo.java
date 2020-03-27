package com.example.demo;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author win10
 */
public class FontDemo extends JFrame {
    private JLabel labels[];
    private String names[] = {"getFamily:", "getFontName:", "getName:",
            "getSize:", "getSize2D:", "getStyle:", "isBold:", "isItalic:",
            "isPlain:", "toString:"};

    public FontDemo() {
        super("FontDemo");
        Font font = new Font("楷体", Font.ROMAN_BASELINE, 30);    //新建一个 Font 对象
        Container container = getContentPane();
        container.setLayout(new GridLayout(10, 2, 10, 10));
        labels = new JLabel[20];
        for (int count = 0; count < 10; count++) {
            labels[count * 2] = new JLabel(names[count]);
        }
        labels[1] = new JLabel(font.getFamily());        //10个常用方法的结果
        labels[3] = new JLabel(font.getFontName());
        labels[5] = new JLabel(font.getName());
        labels[7] = new JLabel("" + font.getSize());
        labels[9] = new JLabel("" + font.getSize2D());
        labels[11] = new JLabel("" + font.getStyle());
        labels[13] = new JLabel("" + font.isBold());
        labels[15] = new JLabel("" + font.isItalic());
        labels[17] = new JLabel("" + font.isPlain());
        labels[19] = new JLabel(font.toString());
        for (int count = 0; count < 20; count++) {
            labels[count].setFont(font);                //将文本设置为该字体
            container.add(labels[count]);
        }
        setVisible(true);
        setSize(800, 600);
    }

    public static void main(String[] args) {
        FontDemo application = new FontDemo();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
