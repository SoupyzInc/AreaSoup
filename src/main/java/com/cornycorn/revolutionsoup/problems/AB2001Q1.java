package com.cornycorn.revolutionsoup.problems;

import java.util.Scanner;

/**
 * AB 2001 FRQ #1 from the Type 4 FRQs Packet.
 */
public class AB2001Q1 {
    private static final String NAME = "AB 2001 #1";
    private static final String PROBLEM = "Let R and S be the regions in the first quadrant shown in the figure " +
            "above. The region R is bounded by the x-axis and the graphs of y = 2 - x^3 and y = tan x. The region S" +
            " is bounded by the y-axis and the graphs of y = 2 - x^3 and y = tan x.";
    private static final String A = "(a) FInd the area of R.";
    private static final String B = "(b) Find the area of S.";
    private static final String C = "(c) Find the volume of the solid generated when S is revolved about the x-axis.";

    private static final String INVALID_PROBLEM = " is not a valid problem.";
    private static final String DIVIDER = "---------------------------------------------------------------";

    /**
     * Begins solving this problem.
     */
    public static void solve() {
        boolean exit = false;
        while (!exit) {
            System.out.println(DIVIDER + DIVIDER + "\n" + NAME);
            System.out.println(PROBLEM + "\n");
            System.out.println(A);
            System.out.println(B);
            System.out.println(C);
            System.out.println(DIVIDER + "\n" + "Solve (a), (b), or (c)? (q to quit)");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            switch (input) {
                case "a" -> solveA();
                case "b" -> solveB();
                case "c" -> solveC();
                case "q" -> exit = true;
                default -> System.out.println(input + INVALID_PROBLEM);
            }
        }
    }

    /**
     * Solves part (a).
     */
    public static void solveA() {
        String ASolution = "The problem gives us the region that R is bounded to. The solution is as simple as " +
                "calculating the area between two curves.\n\n" +
                "You should first notice that the region R has three total 'things' binding it: the " +
                "x-axis, y = tan x, and y = 2 - x^3. We should also notice that half way through R, the top curve " +
                "changes, oh no! This means that we will need to take the sum of two integrals.\n\n" +
                "Our first integral will have a top function of y = tan x and bottom function of y = 0. " +
                "Our limits of integration are from 0 to where the top curve changes from y = tan x to y = 2 - x^3. " +
                "The upper limit of integration can be calculated by finding where the two top functions intersect. " +
                "Here, that is where \n\ttan x = 2 - x^3\n";

        System.out.println(ASolution);
    }

    /**
     * Solves part (b).
     */
    public static void solveB() {
        System.out.println("b");
    }

    /**
     * Solves part (c).
     */
    public static void solveC() {
        System.out.println("c");
    }

    /**
     * Main method for debugging.
     */
    public static void main(String[] args) {
        AB2001Q1.solve();
    }
}
