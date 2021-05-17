package com.cornycorn.areasoup;

import com.cornycorn.areasoup.functions.Function;
import com.cornycorn.areasoup.functions.Squared;
import com.cornycorn.areasoup.functions.X;
import com.cornycorn.areasoup.functions.XSquared;

import java.util.ArrayList;
import java.util.List;

public class AreaSoup {
    private static final int N = 10000000;

    /**
     * Integrates the function, f, from a to b using Simpson's rule.
     *
     * @param a        The lower limit of integration.
     * @param b        The upper limit of integration.
     * @param function The function to integrate.
     * @return The area under the curve from a to b.
     */
    public static double integrate(double a, double b, Function function) {
        double h = (b - a) / (N - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * function.f(x);
        }

        // 2/3 terms.
        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * function.f(x);
        }

        return sum * h;
    }

    /**
     * Returns the points to display the left rectangles of a function.
     *
     * @param interval The interval or width of the rectangles.
     * @param dataPoints The total amount of data points to be calculated.
     * @param function The function to find the left rectangles of.
     * @return The points to graph the left rectangles.
     */
    public static List<Double> leftRiemannSum(int interval, int dataPoints, Function function) {
        List<Double> datas = new ArrayList<>();
        int nextInterval = interval;
        double point = function.f(0);
        for (int i = 0; i < dataPoints; i++) {
            if (i == nextInterval - 1) {
                point = 0;
            } else if (i == nextInterval) {
                point = function.f(i);
                nextInterval += interval;
            }

            datas.add(point);
        }

        return datas;
    }

    /**
     * Returns the points to display the right rectangles of a function.
     *
     * @param interval The interval or width of the rectangles.
     * @param dataPoints The total amount of data points to be calculated.
     * @param function The function to find the right rectangles of.
     * @return The points to graph the right rectangles.
     */
    public static List<Double> rightRiemannSum(int interval, int dataPoints, Function function) {
        List<Double> datas = new ArrayList<>();
        int nextInterval = interval;
        double point = function.f(dataPoints);
        for (int i = dataPoints; i > -1; i--) {
            if ((dataPoints - i) == nextInterval - 1) {
                point = 0;
            } else if ((dataPoints - i) == nextInterval) {
                point = function.f(i);
                nextInterval += interval;
            }

            datas.add(0, point);
        }

        return datas;
    }

    /**
     * Returns the points to display the trapezoidal approximation of a function.
     *
     * @param interval The interval or width of the trapezoids.
     * @param dataPoints The total amount of data points to be calculated.
     * @param function The function to find the trapezoidal approximation of.
     * @return The points to graph the trapezoidal approximation.
     */
    public static List<Double> trapezoidal(int interval, int dataPoints, Function function) {
        // TODO: Fix this.
        List<Double> datas = new ArrayList<>();

        double slope = (function.f(interval) - function.f(0)) / interval;
        int nextInterval = interval;
        double point;
        double b = 0;

        for (int x = 0; x < dataPoints; x++) {
            if (x == nextInterval - 1) {
                point = 0;
            } else if (x == nextInterval) {
                point = function.f(x);
                nextInterval += interval;
                slope = (function.f(nextInterval) - function.f(nextInterval - interval)) / interval;
                b = function.f(x) - (slope * x);
            } else {
                point = slope * x + b;
            }

            datas.add(point);
        }

        return datas;
    }

    /**
     * Returns the approximate area under the curve using the left Riemann sum rule with n rectangles.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the left Riemann sum.
     * @param n The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the left Riemann sum rule.
     */
    public static double leftRiemannSumArea( double a, double b, Function function, int n ) {
        double sum = 0;
        for (double x = a; x < b; x += ((b - a) / n)) {
            sum += function.f(x);
        }
        return ((b - a)/n) * sum;
}
    /**
     * Returns the approximate area under the curve using the right Riemann sum rule with n rectangles.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the right Riemann sum.
     * @param n The number of rectangles being used to estimate the area under the curve.
     * @return The approximate area under the curve by the right Riemann sum rule.
     */
    public static double rightRiemannSumArea( double a, double b, Function function, int n ) {
        double sum = 0;
        for (double x = (a + ((b-a)/n)); x <= b+0.00001; x += ((b - a) / n)) {
            sum += function.f(x);
        }
        return ((b - a)/n) * sum;
    }

    /**
     * Returns the approximate area under the curve using the trapezoidal sum rule with n trapezoids.
     *
     * @param a The lower limit of integration.
     * @param b The upper limit of integration.
     * @param function The function being used to calculate the trapezoidal sum.
     * @param n The number of trapezoids being used to estimate the area under the curve.
     * @return The approximate area under the curve by the trapezoidal sum rule.
     */
    public static double trapezoidalArea( double a, double b, Function function, int n ) {
        double sum = 0;
        for (double x = a; x <= b+0.00001; x += ((b - a) / n)) {
            if( x == a || x >= b ) {
                sum += function.f(x);
            }
            else
                sum += 2*(function.f(x));
        }
        return ((b - a)/(2*n)) * sum;
    }

    public static void main( String[] args ) {
        System.out.println(AreaSoup.trapezoidalArea(0, 6, new XSquared(), 2));
    }
}