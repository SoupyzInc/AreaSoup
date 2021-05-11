public class App {
    public static void main(String[] args) {
        RevolutionSoup rs = new RevolutionSoup(new SinX(), new XAxis(), 10000000);

        // Test 1
        System.out.println(rs.integrate(0, (2 * Math.PI), new SinX()));
        // OUT: 8.820740479912989E-14

        // Maybe we should add a rounding function that will take these super
        // small values and just return 0? For an increased value of N, it
        // returns an even smaller non-zero value.

        // Test 2
        System.out.println(rs.revolution(0, (2*Math.PI), 0));
        // OUT: 9.869604401090964
    }
}
