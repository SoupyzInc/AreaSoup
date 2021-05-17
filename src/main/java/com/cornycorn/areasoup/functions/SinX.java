package com.cornycorn.areasoup.functions;

public class SinX implements Function {
    /**
     * Calculates the sine for a given x-value. Represents f(x) = sin(x), for x in degrees.
     *
     * @param x The x-value in degrees to find the y-value for.
     * @return The sine of x.
     */
    @Override
    public double f( double x ) {
        return Math.sin(x * (Math.PI / 180));
    }
}
