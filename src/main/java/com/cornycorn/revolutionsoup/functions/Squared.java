package com.cornycorn.revolutionsoup.functions;

public abstract class Squared implements Function {
    public double f(double x, Function function) {
        return Math.pow(function.f(x), 2);
    }
}
