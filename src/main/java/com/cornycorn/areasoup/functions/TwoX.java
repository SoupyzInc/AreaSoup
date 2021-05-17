package com.cornycorn.areasoup.functions;

public class TwoX implements Function {
    /**
     * Calculates the y-value for a given x-value. Represents f(x) = 2x.
     *
     * @param x The x-value to find the y-value for.
     * @return 2x.
     */
    @Override
    public double f(double x) {
        return 2 * x;
    }
}
