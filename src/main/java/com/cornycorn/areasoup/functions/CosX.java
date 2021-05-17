package com.cornycorn.areasoup.functions;

public class CosX implements Function {
    /**
     * Calculates the cosine for a given x-value. Represents f(x) = cos(x), for x in degrees.
     *
     * @param x The x-value in degrees to find the y-value for.
     * @return The cosine of x.
     */
    @Override
    public double f( double x ) {
        return Math.cos(x * (Math.PI / 180));
    }
}
