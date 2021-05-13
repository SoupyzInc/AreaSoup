package com.cornycorn.revolutionsoup;

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
    private static Problem problem;

    public static void setPanel(int numberOfParts) {
        JLabel label = new JLabel("Solve: ");
        panel.add(label);

        JButton solveA = new JButton("(a)");
        solveA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ta.setText(problem.solve() + "\n\n" + problem.solveA());
            }
        });
        panel.add(solveA);

        JButton solveB = new JButton("(b)");
        solveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ta.setText(problem.solve());
            }
        });
        panel.add(solveB);

        if (numberOfParts > 2) {
            JButton solveC = new JButton("(C)");
            solveC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    ta.setText(problem.solve());
                }
            });
            panel.add(solveC);
        }

        panel.add(new JSeparator(SwingConstants.VERTICAL));

        JButton clear = new JButton("Clear");
        clear.addActionListener(ev -> {
            ta.setText("");
            panel.removeAll();
        });
        panel.add(clear);
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
        m11.addActionListener(ev -> {
            problem = new AB2001Q1();
            ta.setText(problem.solve());
            setPanel(problem.getNumberOfParts());
        });
        m1.add(m11);

        JMenuItem m22 = new JMenuItem("AB 2002 #1");
        m22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
//                problem = new AB2002Q1();
//                ta.setText(problem.solve());
//                setPanel(problem.getNumberOfParts());
            }
        });
        m1.add(m22);

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
