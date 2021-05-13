package com.cornycorn.revolutionsoup;

import com.cornycorn.revolutionsoup.functions.*;
import com.cornycorn.revolutionsoup.problems.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class App extends JFrame implements ActionListener {
    private static final JFrame frame = new JFrame("RevolutionSoup");
    private static final JTextArea ta = new JTextArea();
    private static final JMenuBar mb = new JMenuBar();
    private static final JPanel panel = new JPanel();

    public void setButtons(int numberOfParts) {
        if (numberOfParts > 2) {

        } else if (numberOfParts > 1) {

        } else {

        }
    }

    public static void initialize() {
        // Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Text area
        ta.setFont(new Font("Times New Roman", Font.PLAIN,24));
        ta.setBorder(new EmptyBorder(5, 10, 10, 10));
        ta.setEditable(false);

        // Menu bar
        JMenu m1 = new JMenu("Problem");
        mb.add(m1);

        // Problems
        JMenuItem m11 = new JMenuItem("AB 2001 #1");
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ta.setText(AB2001Q1.solve());
            }
        });
        m1.add(m11);

        JMenuItem m22 = new JMenuItem("AB 2002 #1");
        m22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.out.println("2002");
            }
        });
        m1.add(m22);

        // Bottom panel
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Add components to frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        initialize();
    }
}
