import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Function f;
        RevolutionSoup rs = new RevolutionSoup(100000);

        f = new TwoX();
        rs.setFunction(f);
        System.out.println(rs.integrate(1, 2));

        f = new XSquared();
        rs.setFunction(f);
        System.out.println(rs.integrate(1, 2));

        f = new XCubed();
        rs.setFunction(f);
        System.out.println(rs.integrate(1, 2));

//        JFrame f = new JFrame();
//
//        String[] problems = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
//
//        //Create the combo box, select item at index 4.
//        //Indices start at 0, so 4 specifies the pig.
//        JComboBox problemList = new JComboBox(problems);
//        problemList.setSelectedIndex(4);
//        problemList.addActionListener(f);
//
//        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f.setLayout(null);
//        f.setVisible(true);

        // Find the volume of the region bounded by y = x^2, x = 2 and the y = 0 about the x-axis
        // \pi\in_{0}^{2}(x^2)^2 dx

        // 1. Find the region (2d area).
        // 2. Find the limits of integration.
        // 3. Integrate, don't forget pi!
    }
}
