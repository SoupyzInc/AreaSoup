package com.cornycorn.revolutionsoup.problems;

public class Problem {
    private String name, problem, a, b, c, solutionA, solutionB, solutionC;
    private int numberOfParts;

    public Problem(String name, String problem,
                   String a, String b, String c,
                   String solutionA, String solutionB, String solutionC,
                   int numberOfParts) {
        this.name = name;
        this.problem = problem;

        this.a = a;
        this.b = b;
        this.c = c;

        this.solutionA = solutionA;
        this.solutionB = solutionB;
        this.solutionC = solutionC;

        this.numberOfParts = numberOfParts;
    }

    public Problem(String name, String problem,
                   String a, String b,
                   String solutionA, String solutionB,
                   int numberOfParts) {
        this.name = name;
        this.problem = problem;

        this.a = a;
        this.b = b;

        this.solutionA = solutionA;
        this.solutionB = solutionB;

        this.numberOfParts = numberOfParts;
    }

    public String solve() {
        if (numberOfParts > 2) {
            return name + "\n" + problem + "\n" + a + "\n" + b + "\n" + c;
        } else {
            return name + "\n" + problem + "\n" + a + "\n" + b;
        }
    }

    public String solveA() { return solutionA; }

    public String solveB() { return solutionB; }

    public String solveC() { return solutionC; }

    public int getNumberOfParts() { return numberOfParts; }
}
