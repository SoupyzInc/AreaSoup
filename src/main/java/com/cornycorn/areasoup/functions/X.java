package com.cornycorn.areasoup.functions;

public class X implements Function {
    /**
     * Calculates the y-value for a given x-value. Represents f(x) = x.
     *
     * @param x The x-value to find the y-value for.
     * @return x.
     */
    @Override
    public double f(double x) {
        return x;
    }
}
