package com.cornycorn.revolutionsoup.problems;

/**
 * AB 2001 FRQ #1 from the Type 4 FRQs Packet.
 */
public class AB2001Q1 extends Problem {
    public AB2001Q1() {
        super("AB 2001 #1",
                "Let R and S be the regions in the first quadrant shown in the figure " +
                        "above. The region R is bounded by the x-axis and the graphs of y = 2 - x^3 and y = tan x. " +
                        "The region S is bounded by the y-axis and the graphs of y = 2 - x^3 and y = tan x.",
                "(a) Find the area of R.",
                "(b) Find the area of S.",
                "(c) Find the volume of the solid generated when S is revolved about the x-axis.",
                "The problem gives us the region that R is bounded to. The solution is as simple as " +
                        "calculating the area between two curves.\n\n" +
                        "You should first notice that the region R has three total 'things' binding it: the x-axis, " +
                        "y = tan x, and y = 2 - x^3. We should also notice that half way through R, the top curve " +
                        "changes, oh no! This means that we will need to take the sum of two integrals.\n\n" +
                        "Our first integral will have a top function of y = tan x and bottom function of y = 0. " +
                        "Our limits of integration are from 0 to where the top curve changes from y = tan x to " +
                        "y = 2 - x^3. The upper limit of integration can be calculated by finding where the two top " +
                        "functions intersect. Here, that is where \n\ttan x = 2 - x^3\n",
                "",
                "",
                3);
    }
}
