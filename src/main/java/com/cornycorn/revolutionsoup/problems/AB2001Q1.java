package com.cornycorn.revolutionsoup.problems;

import java.util.Scanner;

public class AB2001Q1 {
    private static final String NAME = "AB 2001 #1";
    private static final String PROBLEM = "Let R and S be the regions in the first quadrant shown in the figure " +
            "above. The region R is bounded by the x-axis and the graphs of y = 2 - x^3 and y = tan x. The region S" +
            " is bounded by the y-axis and the graphs of y = 2 - x^3 and y = tan x.";
    private static final String A = "(a) FInd the area of R.";
    private static final String B = "(b) Find the area of S.";
    private static final String C = "(c) Find the volume of the solid generated when S is revolved about the x-axis.";

    public void solve() {
        System.out.println(NAME);
        System.out.println(PROBLEM + "\n");
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        boolean exit = false;
        while (!exit) {
            System.out.println("---------------------------------------------------------------\n" +
                    "Solve (a), (b), or (c)? (q to quit)");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            switch (input) {
                case "a" -> solveA();
                case "b" -> solveB();
                case "c" -> solveC();
                case "q" -> exit = true;
            }
        }
    }

    public void solveA() {
        System.out.println("a");
    }

    public void solveB() {
        System.out.println("b");
    }

    public void solveC() {
        System.out.println("c");
    }
}
