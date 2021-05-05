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
    }
}
