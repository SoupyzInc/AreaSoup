package com.cornycorn.areasoup.functions;

/**
  An abstract implementation of the <code>Function</code> interface. Used by <code>RevolutionSoup.revolution</code>
  to calculate volume of revolution.
 */
public abstract class Squared implements Function {
    /**
     * Returns the squared value of a function.
     *
     * @param x The x-value to find the y-value for.
     * @param function The function to square.
     * @return The y-value for the given x-value.
     */
    public double f(double x, Function function) {
        return Math.pow(function.f(x), 2);
    }
}
