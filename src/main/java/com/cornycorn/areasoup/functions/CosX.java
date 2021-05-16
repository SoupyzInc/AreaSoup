package com.cornycorn.areasoup.functions;

public class CosX implements Function {
    public double f( double x ) {
        return Math.cos(x * (Math.PI / 180));
    }
}
