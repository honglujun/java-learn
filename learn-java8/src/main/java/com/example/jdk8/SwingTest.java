package com.example.jdk8;

import javax.swing.*;

public class SwingTest {

    public static void main(String[] args) {
        System.out.println("hello word");
        JFrame  jFrame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");
        // event是ActionEvent类型，这个是通过jdk推断出来
        jButton.addActionListener(event -> {
            System.out.println("Button Pressed");
            System.out.println("hello word");
            System.out.println("executed");
        });
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
