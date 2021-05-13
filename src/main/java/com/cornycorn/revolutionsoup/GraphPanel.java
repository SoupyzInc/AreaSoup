package com.cornycorn.revolutionsoup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.cornycorn.revolutionsoup.functions.*;

public class GraphPanel extends JPanel {
    private static final int PADDING = 25;
    private static final int LABEL_PADDING = 25;
    private static final Color LINE_COLOR = new Color(44, 102, 230, 180);
    private static final Color POINT_COLOR = new Color(100, 100, 100, 180);
    private static final Color GRID_COLOR = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static final int POINT_WIDTH = 4;
    private static final int Y_DIVISIONS = 10;

    private List<Double> datas;

    public GraphPanel(List<Double> datas) {
        this.datas = datas;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * PADDING) - LABEL_PADDING) / (datas.size() - 1);
        double yScale = ((double) getHeight() - 2 * PADDING - LABEL_PADDING) / (getMaxData() - getMinData());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            int x1 = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y1 = (int) ((getMaxData() - datas.get(i)) * yScale + PADDING);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(PADDING + LABEL_PADDING, PADDING, getWidth() - (2 * PADDING) - LABEL_PADDING, getHeight() - 2 * PADDING - LABEL_PADDING);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < Y_DIVISIONS + 1; i++) {
            int x0 = PADDING + LABEL_PADDING;
            int x1 = POINT_WIDTH + PADDING + LABEL_PADDING;
            int y0 = getHeight() - ((i * (getHeight() - PADDING * 2 - LABEL_PADDING)) / Y_DIVISIONS + PADDING + LABEL_PADDING);
            int y1 = y0;
            if (datas.size() > 0) {
                g2.setColor(GRID_COLOR);
                g2.drawLine(PADDING + LABEL_PADDING + 1 + POINT_WIDTH, y0, getWidth() - PADDING, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinData() + (getMaxData() - getMinData()) * ((i * 1.0) / Y_DIVISIONS)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < datas.size(); i++) {
            if (datas.size() > 1) {
                int x0 = i * (getWidth() - PADDING * 2 - LABEL_PADDING) / (datas.size() - 1) + PADDING + LABEL_PADDING;
                int x1 = x0;
                int y0 = getHeight() - PADDING - LABEL_PADDING;
                int y1 = y0 - POINT_WIDTH;
                if ((i % ((int) ((datas.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(GRID_COLOR);
                    g2.drawLine(x0, getHeight() - PADDING - LABEL_PADDING - 1 - POINT_WIDTH, x1, PADDING);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(PADDING + LABEL_PADDING, getHeight() - PADDING - LABEL_PADDING, PADDING + LABEL_PADDING, PADDING);
        g2.drawLine(PADDING + LABEL_PADDING, getHeight() - PADDING - LABEL_PADDING, getWidth() - PADDING, getHeight() - PADDING - LABEL_PADDING);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(LINE_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - POINT_WIDTH / 2;
            int ovalW = POINT_WIDTH;
            int ovalH = POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
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

    public void setDatas(List<Double> datas) {
        this.datas = datas;
        invalidate();
        this.repaint();
    }

    public List<Double> getDatas() {
        return datas;
    }

    private static void createAndShowGui() {
        // Get values
        List<Double> datas = new ArrayList<>();
        int maxDataPoints = 360;
        for (int i = 0; i < maxDataPoints; i++) {
            Function func = new SinX();
            datas.add(func.f(i * (Math.PI / 180)));
        }

        GraphPanel mainPanel = new GraphPanel(datas);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("RevolutionSoup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}