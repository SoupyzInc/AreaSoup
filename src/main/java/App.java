public class App {
    public static void main(String[] args) {
        Function f;
        RevolutionSoup rs = new RevolutionSoup(100000);

        f = new XSquared();
        rs.setFunction(f);
        System.out.println(rs.integrate(1, 2));

        f = new XCubed();
        rs.setFunction(f);
        System.out.println(rs.integrate(1, 2));

        // Find the volume of the region bounded by y = x^2, x = 2 and the y = 0 about the x-axis
        // \pi\in_{0}^{2}(x^2)^2 dx

        // 1. Find the region (2d area).
        // 2. Find the limits of integration.
        // 3. Integrate, don't forget pi!
    }
}
