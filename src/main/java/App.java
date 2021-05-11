public class App {
    public static void main(String[] args) {
        RevolutionSoup rs = new RevolutionSoup(new SinX(), new XAxis(), 10000000);
        System.out.println(rs.revolution(0, (2*Math.PI), 0));


        // Find the volume of the region bounded by y = x^2, x = 2 and y = 0 about the x-axis
        // \pi\in_{0}^{2}(x^2)^2 dx

        // 1. Find the region (2d area).
        // 2. Find the limits of integration.
        // 3. Integrate, don't forget pi!
    }
}
