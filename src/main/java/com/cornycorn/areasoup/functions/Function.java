package com.cornycorn.areasoup.functions;

/*
 * The Function interface for all other functions to be based off of.
 */
public interface Function {
    /**
     * Calculates the y-value for a given x-value of this function.
     * @param x The x-value to find the y-value for.
     * @return The y-value for the given x-value.
     */
    double f(double x);
}