package com.cornycorn.areasoup.functions;

public class XCubed implements Function {
    /**
     * Calculates the y-value for a given x-value. Represents f(x) = x^3.
     *
     * @param x The x-value to find the y-value for.
     * @return x^3.
     */
    @Override
    public double f(double x) {
        return Math.pow(x, 3);
    }
}
