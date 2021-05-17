package com.cornycorn.areasoup.functions;

public class XAxis implements Function {
    /**
     * Calculates the y-value for a given x-value. Represents f(x) = 0 or the x-axis.
     *
     * @param x The x-value to find the y-value for.
     * @return 0.
     */
    @Override
    public double f( double x ) {
        return 0;
    }
}
