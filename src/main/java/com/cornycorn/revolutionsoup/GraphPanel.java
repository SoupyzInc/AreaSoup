package com.cornycorn.revolutionsoup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import com.cornycorn.revolutionsoup.functions.*;

public class GraphPanel extends JPanel {
    private static final int PADDING = 25;
    private static final int LABEL_PADDING = 25;

    private static final Color LINE_COLOR_MAIN = new Color(44, 102, 230, 180);
    private static final Color LINE_COLOR_SECONDARY = new Color(215, 128, 84, 180);
    private static final Color LINE_COLOR_OVER = new Color(77, 210, 62, 180);
    private static final Color LINE_COLOR_UNDER = new Color(210, 62, 62, 180);
    private static final Color GRID_COLOR = new Color(200, 200, 200, 200);

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private static final int POINT_WIDTH = 4;
    private static final int Y_DIVISIONS = 10;

    private static JMenuBar mb = new JMenuBar();
    private static JPanel panel = new JPanel();

    private static Function function = new SinX();
    private static Method method = Method.LEFT;
    private static int interval = 20;
    private static int maxDataPoints = 361;

    private static GraphPanel mainPanel;

    private enum Method {
        LEFT, RIGHT, TRAPEZOID, NONE
    }

    private List<Double> datas;
    private List<Double> approximationData;

    public GraphPanel(List<Double> datas, List<Double> approximationData) {
        this.datas = datas;
        this.approximationData = approximationData;
    }

    public static void setData(List<Double> datas, List<Double> approximationData) {
        mainPanel.datas = datas;
        mainPanel.approximationData = approximationData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * PADDING) - LABEL_PADDING) / (datas.size() - 1);
        double yScale = ((double) getHeight() - 2 * PADDING - LABEL_PADDING) / (getMaxData() - getMinData());

        // Set graph points
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            int x1 = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y1 = (int) ((getMaxData() - datas.get(i)) * yScale + PADDING);
            graphPoints.add(new Point(x1, y1));
        }

        List<Point> approximationPoints = new ArrayList<>();
        for (int i = 0; i < approximationData.size(); i++) {
            int x1 = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y1 = (int) ((getMaxData() - approximationData.get(i)) * yScale + PADDING);
            approximationPoints.add(new Point(x1, y1));
        }

        // Fill background.
        g2.setColor(Color.WHITE);
        g2.fillRect(PADDING + LABEL_PADDING, PADDING, getWidth() - (2 * PADDING) - LABEL_PADDING, getHeight() - 2 * PADDING - LABEL_PADDING);
        g2.setColor(Color.BLACK);

        // Y axis grid marks and hatch lines.
        for (int i = 0; i < Y_DIVISIONS + 1; i++) {
            int x0 = PADDING + LABEL_PADDING;
            int x1 = POINT_WIDTH + PADDING + LABEL_PADDING;
            int y0 = getHeight() - ((i * (getHeight() - PADDING * 2 - LABEL_PADDING)) / Y_DIVISIONS + PADDING + LABEL_PADDING);
            if (datas.size() > 0) {
                g2.setColor(GRID_COLOR);
                g2.drawLine(PADDING + LABEL_PADDING + 1 + POINT_WIDTH, y0, getWidth() - PADDING, y0);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinData() + (getMaxData() - getMinData()) * ((i * 1.0) / Y_DIVISIONS)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y0);
        }

        // X axis grid marks and hatch lines.
        for (int i = 0; i < datas.size(); i++) {
            if (datas.size() > 1) {
                int x0 = i * (getWidth() - PADDING * 2 - LABEL_PADDING) / (datas.size() - 1) + PADDING + LABEL_PADDING;
                int y0 = getHeight() - PADDING - LABEL_PADDING;
                int y1 = y0 - POINT_WIDTH;
                if ((i % ((int) ((datas.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(GRID_COLOR);
                    g2.drawLine(x0, getHeight() - PADDING - LABEL_PADDING - 1 - POINT_WIDTH, x0, PADDING);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x0, y1);
            }
        }

        // X and Y axis.
        g2.drawLine(PADDING + LABEL_PADDING, getHeight() - PADDING - LABEL_PADDING, PADDING + LABEL_PADDING, PADDING);
        g2.drawLine(PADDING + LABEL_PADDING, getHeight() - PADDING - LABEL_PADDING, getWidth() - PADDING, getHeight() - PADDING - LABEL_PADDING);

        // Graph approximation
        g2.setColor(LINE_COLOR_SECONDARY);
        for (int i = 0; i < approximationPoints.size() - 1; i++) {
            int x1 = approximationPoints.get(i).x;
            int y1 = approximationPoints.get(i).y;
            int x2 = approximationPoints.get(i + 1).x;
            int y2 = approximationPoints.get(i + 1).y;

            // TODO: Fix colors.
//            if (y2 > graphPoints.get(i + 1).y) {
//                g2.setColor(LINE_COLOR_OVER);
//            } else if (y2 < graphPoints.get(i + 1).y) {
//                g2.setColor(LINE_COLOR_UNDER);
//            } else {
//                g2.setColor(LINE_COLOR_SECONDARY);
//            }

            g2.drawLine(x1, y1, x2, y2);
        }

        // Graph data
        g2.setColor(LINE_COLOR_MAIN);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private double getMinData() {
        double minData = Double.MAX_VALUE;
        for (Double data : datas) {
            minData = Math.min(minData, data);
        }
        return minData;
    }

    private double getMaxData() {
        double maxData = Double.MIN_VALUE;
        for (Double data : datas) {
            maxData = Math.max(maxData, data);
        }
        return maxData;
    }

    private static void setValues() {
        // Set values
        List<Double> newDatas = new ArrayList<>();
        for (int i = 0; i < maxDataPoints; i++) {
            newDatas.add(function.f(i * (Math.PI / 180)));
        }

        List<Double> newApproximationData;
        switch (method) {
            case LEFT -> newApproximationData = RevolutionSoup.leftRiemannSum(interval, maxDataPoints, function);
            case RIGHT -> newApproximationData = RevolutionSoup.rightRiemannSum(interval, maxDataPoints, function);
            default -> newApproximationData = newDatas;
        }

        setData(newDatas, newApproximationData);
        mainPanel.repaint();
    }

    private static void setProblems() {
        // Functions
        JMenu fm = new JMenu("Function");
        mb.add(fm);

        JMenuItem twoXItem = new JMenuItem("2x");
        twoXItem.addActionListener(ev -> {
            function = new TwoX();
            setValues();
        });
        fm.add(twoXItem);

        JMenuItem xSquaredItem = new JMenuItem("x²");
        xSquaredItem.addActionListener(ev -> {
            function = new XSquared();
            setValues();
        });
        fm.add(xSquaredItem);

        JMenuItem xCubedItem = new JMenuItem("x³");
        xCubedItem.addActionListener(ev -> {
            function = new XCubed();
            setValues();
        });
        fm.add(xCubedItem);

        JMenuItem sinItem = new JMenuItem("sin(x)");
        sinItem.addActionListener(ev -> {
            function = new SinX();
            setValues();
        });
        fm.add(sinItem);

        JMenuItem cosItem = new JMenuItem("cos(x)");
        cosItem.addActionListener(ev -> {
            function = new CosX();
            setValues();
        });
        fm.add(cosItem);

        // Approximation methods
        JMenu am = new JMenu("Approximation");
        mb.add(am);

        JMenuItem leftItem = new JMenuItem("Left Rectangles");
        leftItem.addActionListener(ev -> {
            method = Method.LEFT;
            setValues();
        });
        am.add(leftItem);

        JMenuItem rightItem = new JMenuItem("Right Rectangles");
        rightItem.addActionListener(ev -> {
            method = Method.RIGHT;
            setValues();
        });
        am.add(rightItem);

        JMenuItem trapItem = new JMenuItem("Trapezoids");
        trapItem.addActionListener(ev -> {
            method = Method.TRAPEZOID;
            setValues();
        });
        am.add(trapItem);

        JMenuItem noneItem = new JMenuItem("None");
        noneItem.addActionListener(ev -> {
            method = Method.NONE;
            setValues();
        });
        am.add(noneItem);
    }

    public static void setPanel() {
        JLabel deltaXLabel = new JLabel("∆x: ");
        panel.add(deltaXLabel);

        JTextField deltaXText = new JTextField(3);
        deltaXText.addActionListener(ev -> {
            interval = Integer.parseInt(deltaXText.getText());
            setValues();
        });
        panel.add(deltaXText);

        panel.add(new JSeparator(SwingConstants.VERTICAL));

        JLabel domainLabelPre = new JLabel("Domain: [0, ");
        panel.add(domainLabelPre);

        JTextField domainText = new JTextField(3);
        domainText.addActionListener(ev -> {
            maxDataPoints = Integer.parseInt(domainText.getText()) + 1;
            setValues();
        });
        panel.add(domainText);

        JLabel domainLabelPost = new JLabel("] (Integer in degrees)");
        panel.add(domainLabelPost);

        panel.add(new JSeparator(SwingConstants.VERTICAL));
    }

    public static void initialize() {
        // Set values
        List<Double> newDatas = new ArrayList<>();
        for (int i = 0; i < maxDataPoints; i++) {
            newDatas.add(function.f(i * (Math.PI / 180)));
        }

        List<Double> newApproximationData;
        switch (method) {
            case LEFT -> newApproximationData = RevolutionSoup.leftRiemannSum(interval, maxDataPoints, function);
            case RIGHT -> newApproximationData = RevolutionSoup.rightRiemannSum(interval, maxDataPoints, function);
            case TRAPEZOID -> newApproximationData = RevolutionSoup.trapezoidal(interval, maxDataPoints, function);
            default -> newApproximationData = newDatas;
        }

        mainPanel = new GraphPanel(newDatas, newApproximationData);

        // Set GUI

        JFrame frame = new JFrame("RevolutionSoup");

        setProblems();
        setPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphPanel::initialize);
    }
}