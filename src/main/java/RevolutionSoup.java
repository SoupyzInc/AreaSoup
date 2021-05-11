public class RevolutionSoup {
    private Function functionTop, functionBottom;
    private final int N;

    /**
     * Instantiates a new instance of RevolutionSoup to solve volume of revolution problems.
     *
     * @param functionTop    The top function.
     * @param functionBottom The bottom function.
     * @param N              The accuracy value.
     */
    public RevolutionSoup(Function functionTop, Function functionBottom, int N) {
        this.functionTop = functionTop;
        this.functionBottom = functionBottom;
        this.N = N;
    }

    public void setFunctionTop(Function functionTop) {
        this.functionTop = functionTop;
    }

    public void setFunctionBottom(Function functionBottom) {
        this.functionBottom = functionBottom;
    }

    /**
     * Integrates the function, f, from a to b using Simpson's rule.
     *
     * @param a        The upper limit of integration.
     * @param b        The lower limit of integration.
     * @param function The function to integrate.
     * @return The area under curve from a to b.
     */
    public double integrate(double a, double b, Function function) {
        double h = (b - a) / (N - 1); // Step size.

        // 1/3 terms.
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms.
        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * function.f(x);
        }

        // 2/3 terms.
        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * function.f(x);
        }

        return sum * h;
    }

    /**
     * Calculates the volume of revolution for the top function and bottom function
     * of this instance of RevolutionSoup from a to b about the x-axis.
     *
     * @param a    The upper limit of integration.
     * @param b    The lower limit of integration.
     * @param axis The y/x value of the axis of rotation where 0 is about the x/y-axis.
     * @return The volume of revolution.
     */
    public double revolution(double a, double b, double axis) {
        // The top function squared with the axis offset.
        Function squareFunctionTop = new Squared() {
            @Override
            public double f(double x) {
                return Math.pow(axis - functionTop.f(x), 2);
            }
        };

        // The bottom function squared with the axis offset.
        Function squareFunctionBottom = new Squared() {
            @Override
            public double f(double x) {
                return Math.pow(axis - functionBottom.f(x), 2);
            }
        };

        // Split the volume of revolution formula into two separate integrals.
        return Math.PI * (integrate(a, b, squareFunctionTop) - integrate(a, b, squareFunctionBottom));
    }
}
