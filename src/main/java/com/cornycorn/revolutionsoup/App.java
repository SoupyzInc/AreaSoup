package com.cornycorn.revolutionsoup;

import com.cornycorn.revolutionsoup.functions.*;
import com.cornycorn.revolutionsoup.problems.*;

public class App {
    public static void main(String[] args) {
        System.out.println(RevolutionSoup.integrate(0, 2 * Math.PI, new SinX()));
        // OUT: 8.820740479912989E-14

        RevolutionSoup.setN(10);
        System.out.println(RevolutionSoup.integrate(0, 2 * Math.PI, new SinX()));
        // OUT: 0.08469971956267101

        System.out.println(RevolutionSoup.revolution(0, 2 * Math.PI, 0, new SinX(), new XAxis()));
        // OUT: 9.869604401090964
    }
}
