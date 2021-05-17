package com.cornycorn.areasoup;

import com.cornycorn.areasoup.functions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    // Enums
    private enum Method {
        LEFT, RIGHT, TRAPEZOID, NONE
    }

    private enum Mode {
        GRAPH, CALCULATE
    }

    // Unicode constants
    private static final String DELTA = "\u2206";
    private static final String APPROACHES = "\u2192";
    private static final String INTEGRAL = "\u222B";
    private static final String RADICAL = "\u222B";
    private static final String SQUARED = "\u00B2";
    private static final String CUBED = "\u00B2";

    // Graphing constants
    private static final int PADDING = 25;
    private static final int LABEL_PADDING = 25;

    private static final Color LINE_COLOR_MAIN = new Color(44, 102, 230, 180);
    private static final Color LINE_COLOR_SECONDARY = new Color(255, 82, 14, 180);
    private static final Color GRID_COLOR = new Color(200, 200, 200, 200);

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    private static final int POINT_WIDTH = 4;
    private static final int Y_DIVISIONS = 10;

    // Frame
    private static JFrame frame = new JFrame("AreaSoup");
    private static JMenuBar mb = new JMenuBar();

    // Graph objects
    private static GraphPanel mainPanel;
    private static JPanel panel = new JPanel();

    // Calculate objects
    private static JPanel calcPanel = new JPanel();
    private static JPanel answerPanel = new JPanel();
    private static JLabel infoLabel = new JLabel();
    private static JLabel integralLabel;

    // Static variables
    private static Function function = new SinX();
    private static String functionName = "sin(x)";

    private static String methodName = "left Riemann sums";

    private static Method method = Method.LEFT;
    private static Mode mode = Mode.GRAPH;

    private static int interval = 20;
    private static int maxDataPoints = 361;

    private List<Double> datas;
    private List<Double> approximationData;

    private static double a = 0;
    private static double b = 1;

    /**
     * Instantiates a new <code>GraphPanel</code> object with data to be graphed.
     * @param datas The y-values of the main function.
     * @param approximationData The y-values of the function approximation.
     * @see #datas
     * @see #approximationData
     */
    public GraphPanel(List<Double> datas, List<Double> approximationData) {
        this.datas = datas;
        this.approximationData = approximationData;
    }

    /**
     * Sets new data to be graphed.
     * @param datas The y-values of the main function.
     * @param approximationData The y-values of the function approximation.
     * @see #datas
     * @see #approximationData
     */
    public static void setData(List<Double> datas, List<Double> approximationData) {
        mainPanel.datas = datas;
        mainPanel.approximationData = approximationData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mode == Mode.GRAPH) {
            // Clear panels
            calcPanel.removeAll();
            calcPanel.revalidate();

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
            g2.setStroke(GRAPH_STROKE);
            for (int i = 0; i < approximationPoints.size() - 1; i++) {
                int x1 = approximationPoints.get(i).x;
                int y1 = approximationPoints.get(i).y;
                int x2 = approximationPoints.get(i + 1).x;
                int y2 = approximationPoints.get(i + 1).y;

                g2.drawLine(x1, y1, x2, y2);
            }

            // Graph data
            g2.setColor(LINE_COLOR_MAIN);
            for (int i = 0; i < graphPoints.size() - 1; i++) {
                int x1 = graphPoints.get(i).x;
                int y1 = graphPoints.get(i).y;
                int x2 = graphPoints.get(i + 1).x;
                int y2 = graphPoints.get(i + 1).y;
                g2.drawLine(x1, y1, x2, y2);
            }
        } else {
            // Clear panels
            panel.removeAll();
            panel.revalidate();
            mainPanel.removeAll();
            mainPanel.revalidate();

            calcPanel.removeAll();
            calcPanel.revalidate();
            answerPanel.removeAll();
            answerPanel.revalidate();

            // Add calculate features
            JLabel aLabel = new JLabel("a:");
            calcPanel.add(aLabel);

            JTextField aText = new JTextField(Double.toString(a), 3);
            aText.addActionListener(ev -> {
                a = Double.parseDouble(aText.getText());
                setAnswer();
            });
            calcPanel.add(aText);

            JLabel bLabel = new JLabel("b:");
            calcPanel.add(bLabel);

            JTextField bText = new JTextField(Double.toString(b), 3);
            bText.addActionListener(ev -> {
                b = Double.parseDouble(bText.getText());
                setAnswer();
            });
            calcPanel.add(bText);

            double answer = AreaSoup.integrate(a, b, function);

            integralLabel = new JLabel(INTEGRAL + " " + functionName + " dx = " + answer);
            calcPanel.add(integralLabel);

            frame.getContentPane().add(calcPanel);
        }
    }

    /**
     * Finds the smallest data point in <code>datas</code> to allow for proper scaling when graphing.
     * @return The smallest data point.
     * @see #datas
     */
    private double getMinData() {
        double minData = Double.MAX_VALUE;
        for (Double data : datas) {
            minData = Math.min(minData, data);
        }
        return minData;
    }

    /**
     * Finds the greatest data point in <code>datas</code> to allow for proper scaling when graphing.
     * @return The greatest data point.
     * @see #datas
     */
    private double getMaxData() {
        double maxData = Double.MIN_VALUE;
        for (Double data : datas) {
            maxData = Math.max(maxData, data);
        }
        return maxData;
    }

    /**
     * Sets new values to be graphed when the function, interval, or data points change.
     */
    private static void setValues() {
        // Set values
        List<Double> newDatas = new ArrayList<>();
        for (int i = 0; i < maxDataPoints; i++) {
            newDatas.add(function.f(i));
        }

        List<Double> newApproximationData;
        switch (method) {
            case LEFT:
                newApproximationData = AreaSoup.leftRiemannSum(interval, maxDataPoints, function);
                break;
            case RIGHT:
                newApproximationData = AreaSoup.rightRiemannSum(interval, maxDataPoints, function);
                break;
            case TRAPEZOID:
                newApproximationData = AreaSoup.trapezoidal(interval, maxDataPoints, function);
                break;
            default:
                newApproximationData = newDatas;
        }

        setData(newDatas, newApproximationData);
        mainPanel.repaint();
    }

    /**
     * Adds a Mode <code>JMenuItem</code> to <code>JMenuBar mb</code>.
     * @see #mb
     */
    private static void addModes() {
        JMenu mm = new JMenu("Mode");
        mb.add(mm);

        JMenuItem graphItem = new JMenuItem("Graph");
        graphItem.addActionListener(ev -> {
            mode = Mode.GRAPH;
            setValues();
            addPanel();
        });
        mm.add(graphItem);

        JMenuItem calcItem = new JMenuItem("Calculate");
        calcItem.addActionListener(ev -> {
            mode = Mode.CALCULATE;
            mainPanel.repaint();
        });
        mm.add(calcItem);
    }

    /**
     * Adds a Function <code> JMenuItem</code> to <code>JMenuBar mb</code>.
     * @see #mb
     */
    private static void addFunctions() {
        JMenu fm = new JMenu("Function");
        mb.add(fm);

        JMenuItem twoXItem = new JMenuItem("2x");
        twoXItem.addActionListener(ev -> {
            function = new TwoX();
            functionName = "2x";
            updateInfoLabel();
            setValues();
        });
        fm.add(twoXItem);

        JMenuItem xSquaredItem = new JMenuItem("x" + SQUARED);
        xSquaredItem.addActionListener(ev -> {
            function = new XSquared();
            functionName = "x" + SQUARED;
            updateInfoLabel();
            setValues();
        });
        fm.add(xSquaredItem);

        JMenuItem xCubedItem = new JMenuItem("x" + CUBED);
        xCubedItem.addActionListener(ev -> {
            function = new XCubed();
            functionName = "x" + CUBED;
            updateInfoLabel();
            setValues();
        });
        fm.add(xCubedItem);

        JMenuItem SquareRootXItem = new JMenuItem(RADICAL + "x");
        SquareRootXItem.addActionListener(ev -> {
            function = new SquareRootX();
            functionName = RADICAL + "x";
            updateInfoLabel();
            setValues();
        });
        fm.add(SquareRootXItem);

        JMenuItem FastInverseSquareRootItem = new JMenuItem("1/" + RADICAL + "x but fast");
        FastInverseSquareRootItem.addActionListener(ev -> {
            function = new FastInverseSquareRoot();
            functionName = "1/" + RADICAL + "x";
            updateInfoLabel();
            setValues();
        });
        fm.add(FastInverseSquareRootItem);

        JMenuItem sinItem = new JMenuItem("sin(x)");
        sinItem.addActionListener(ev -> {
            function = new SinX();
            functionName = "sin(x)";
            updateInfoLabel();
            setValues();
        });
        fm.add(sinItem);

        JMenuItem cosItem = new JMenuItem("cos(x)");
        cosItem.addActionListener(ev -> {
            function = new CosX();
            functionName = "cos(x)";
            updateInfoLabel();
            setValues();
        });
        fm.add(cosItem);
    }

    /**
     * Adds an Approximation <code>JMenuItem</code> to <code>JMenuBar mb</code>.
     * @see #mb
     */
    private static void addApproximations() {
        JMenu am = new JMenu("Approximation");
        mb.add(am);

        JMenuItem leftItem = new JMenuItem("Left Riemann sums");
        leftItem.addActionListener(ev -> {
            method = Method.LEFT;
            methodName = "left Riemann sums";
            updateInfoLabel();
            setValues();
        });
        am.add(leftItem);

        JMenuItem rightItem = new JMenuItem("Right Riemann sums");
        rightItem.addActionListener(ev -> {
            method = Method.RIGHT;
            methodName = "right Riemann sums";
            updateInfoLabel();
            setValues();
        });
        am.add(rightItem);

        JMenuItem trapItem = new JMenuItem("Trapezoidal approximation");
        trapItem.addActionListener(ev -> {
            method = Method.TRAPEZOID;
            methodName = "trapezoids";
            updateInfoLabel();
            setValues();
        });
        am.add(trapItem);

        JMenuItem noneItem = new JMenuItem("None");
        noneItem.addActionListener(ev -> {
            method = Method.NONE;
            updateInfoLabel();
            setValues();
        });
        am.add(noneItem);
    }

    /**
     * Sets the answer for integral calculations.
     * @see #integralLabel
     */
    private static void setAnswer() {
        double answer = AreaSoup.integrate(a, b, function);
        integralLabel.setText(INTEGRAL + " " + functionName + " dx = " + answer);
    }

    private static void updateInfoLabel() {
        if (method != Method.NONE) {
            infoLabel.setText("Approximating " + functionName + " using " + methodName + "   | ");
        } else {
            infoLabel.setText("Graphing " + functionName + "   | ");
        }

        infoLabel.revalidate();
    }

    /**
     * Creates the panel to change the interval width and domain when graphing.
     * @see #panel
     */
    public static void addPanel() {
        if (method != Method.NONE) {
            infoLabel = new JLabel("Approximating " + functionName + " using " + methodName + "   | ");
        } else {
            infoLabel = new JLabel("Graphing " + functionName + "   | ");
        }
        panel.add(infoLabel);

        JLabel deltaXLabel = new JLabel(DELTA + "x:");
        panel.add(deltaXLabel);

        JTextField deltaXText = new JTextField(Integer.toString(interval), 3);
        deltaXText.addActionListener(ev -> {
            interval = Integer.parseInt(deltaXText.getText());
            setValues();
        });
        panel.add(deltaXText);

        JLabel domainLabelPre = new JLabel("Domain: [0,");
        panel.add(domainLabelPre);

        JTextField domainText = new JTextField(Integer.toString(maxDataPoints - 1), 3);
        domainText.addActionListener(ev -> {
            maxDataPoints = Integer.parseInt(domainText.getText()) + 1;
            setValues();
        });
        panel.add(domainText);

        JLabel domainLabelPost = new JLabel("] (Integer in degrees)");
        panel.add(domainLabelPost);

        JButton infinityButton = new JButton("Take lim " + DELTA + "x " + APPROACHES +" 0");
        infinityButton.addActionListener(ev -> {
            Timer timer = new Timer(50, e -> {
                if(interval > 3) {
                    interval--;
                    deltaXText.setText(Integer.toString(interval));
                    setValues();
                } else {
                    ((Timer)e.getSource()).stop();
                }
            });
            timer.setRepeats(true);
            timer.setDelay(50);
            timer.start();

        });
        panel.add(infinityButton);
    }

    /**
     * Initializes values to be graphed and the GUI.
     */
    public static void initialize() {
        // Set values
        List<Double> newDatas = new ArrayList<>();
        for (int i = 0; i < maxDataPoints; i++) {
            newDatas.add(function.f(i));
        }

        List<Double> newApproximationData;
        switch (method) {
            case LEFT:
                newApproximationData = AreaSoup.leftRiemannSum(interval, maxDataPoints, function);
                break;
            case RIGHT:
                newApproximationData = AreaSoup.rightRiemannSum(interval, maxDataPoints, function);
                break;
            case TRAPEZOID:
                newApproximationData = AreaSoup.trapezoidal(interval, maxDataPoints, function);
                break;
            default:
                newApproximationData = newDatas;
        }

        mainPanel = new GraphPanel(newDatas, newApproximationData);
        mainPanel.setLayout(new java.awt.BorderLayout());

        // Set GUI
        addPanel();

        // Menu bar
        addModes();
        addFunctions();
        addApproximations();

        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
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