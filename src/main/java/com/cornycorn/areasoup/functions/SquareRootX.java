package com.cornycorn.areasoup.functions;

public class SquareRootX implements Function {
    /**
     * Calculates the square root for a given x-value. Represents f(x) = sqrt(x).
     *
     * @param x The x-value to find the y-value for.
     * @return The square root of x.
     */
    @Override
    public double f(double x) {
        return Math.sqrt(x);
    }
}
