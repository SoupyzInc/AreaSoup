package com.cornycorn.revolutionsoup;

import com.cornycorn.revolutionsoup.functions.*;

public class App {
    public static void main(String[] args) {
        RevolutionSoup rs = new RevolutionSoup(new SinX(), new XAxis());

        // Test 1
        System.out.println(rs.integrate(0, 2 * Math.PI, new SinX()));
        // OUT: 8.820740479912989E-14

        // Maybe we should add a rounding function that will take these super
        // small values and just return 0? For an increased value of N, it
        // returns an even smaller non-zero value.

        // Test 2
        System.out.println(rs.revolution(0, 2 * Math.PI, 0));
        // OUT: 9.869604401090964

//         System.out.println("AB 2001 #1");
//         System.out.println("Let R and S be the regions in the first quadrant shown in the figure above. The region R is bounded by the");
//         System.out.println("x-axis and the graphs of y = 2 - x^3 and y = tan x. The region S is bounded by the y-axis and the graphs of");
//         System.out.println("y = 2 - x^3 and y = tan x.");
//         System.out.println();
//         System.out.println("(a) FInd the area of R.");
//         System.out.println("(b) Find the area of S.");
//         System.out.println("(c) Find the volume of the solid generated when S is revolved about the x-axis.");
    }
}
