package com.cornycorn.revolutionsoup.functions;

public class FastInverseSquareRoot implements Function {
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
