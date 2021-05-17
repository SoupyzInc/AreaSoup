package com.cornycorn.areasoup.functions;

public class SinX implements Function {
    @Override
    public double f( double x ) {
        return Math.sin(x * (Math.PI / 180));
    }
}
