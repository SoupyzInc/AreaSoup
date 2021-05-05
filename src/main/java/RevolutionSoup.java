import java.util.Scanner;

public class RevolutionSoup {
    private Function function;
    private int N;

    public RevolutionSoup(int N) {
        this.N = N;
    }

    public RevolutionSoup(Function f, int N) {
        this.function = f;
        this.N = N;
    }

    /**
     * Sets the function for the instance of RevolutionSoup.
     * @param f The function behaviour.
     */
    public void setFunction(Function f) {
        this.function = f;
    }

    /**
     * Integrates the function f of the instance of RevoultionSoup from a to b.
     * @param a The upper limit of integration.
     * @param b The lower limit of integration.
     * @return The area under curve from a to b.
     */
    public double integrate(double a, double b) {
        double h = (b - a) / (N - 1); // step size

        // 1/3 terms
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms
        for (int i = 1; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 4.0 / 3.0 * function.f(x);
        }

        // 2/3 terms
        for (int i = 2; i < N - 1; i += 2) {
            double x = a + h * i;
            sum += 2.0 / 3.0 * function.f(x);
        }

        return sum * h;
    }
}
