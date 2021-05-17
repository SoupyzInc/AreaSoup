package com.cornycorn.areasoup.functions;

/**
 * An implementation of the <code>Function</code> interface. Uses the famous fast inverse square root algorithm
 * from Quake III's source code.
 */
public class FastInverseSquareRoot implements Function {
    /**
     * Calculates the inverse square root for a given x-value. Represents f(x) = 1 / sqrt(x).
     *
     * @param x The x-value to find the y-value for.
     * @return 1 / sqrt(x).
     */
    @Override
    public double f(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);

        i = 0x5fe6ec85e7de30daL - (i >> 1);     // evil floating point bit level hacking
        x = Double.longBitsToDouble(i);         // what the [expletive]?
        x *= (1.5d - xhalf * x * x);            // 1st iteration
        return x;
    }
}
