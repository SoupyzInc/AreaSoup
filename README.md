# RevolutionSoup
Ultimately, a Java app that gives a guided walk through on volume of revolution problems
from the Type 4 FRQs packet, `RevolutionSoup` is a basic calculus engine that can integrate functions and solve
volume of revolution problems.

### Example Usage
```java
import com.cornycorn.revolutionsoup;
import com.cornycorn.revolutionsoup.functions.*;

// Find the volume of the region bounded by y = x^3 and y = x^2, 
// rotated about the line y = 3.
Function topFunction = new XCubed(); // y = x^3
Function bottomFunction = new XSquared(); // y = x^2

double volume = RevolutionSoup.revolution(0, 1, 3, topFunction, bottomFunction);
```

### Adding new functions
To add new functions, create a new class that `implements` the `Function` interface. `Function` is defined under 
[Function.java][function] as
```java
public interface Function {
    double f(double x);
}
```
An example of a cubic function is defined under [XCubed.java][cubed] as

```java
public class XCubed implements Function {
    public double f(double x) {
        return Math.pow(x, 3);
    }
}
```

More examples can be found in the [functions folder][functions].

### Adding new problems
To add new problems, create a new class that `extends` the `Problem` class. `Problem` is defined under 
[Problem.java][problem]. Each problem must have at least a part (a) and (b), and at most up to a part (c). Appropriately
set `numberOfParts` to the number of parts present, either 2 or 3. For examples of problems, refer to the
[problems folder][problems].

You must now add code to access your new problem in the GUI. Under [`App.java`][app], in the `setProblems` method, add a
new `JMenuItem` for the problem like so (replace the `X` in `m1X` with the next available number).
```java
JMenuItem m1X = new JMenuItem("Problem Name");
m1X.addActionListener(ev -> {
    problem = new ProblemName(); // The problem class you made.
    ta.setText(problem.solve());
    setPanel(problem.getNumberOfParts());
});
m1.add(m1X);
```

The rest of the code should handle everything else for you.

### Attribution
This project was made by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] for Mr. Adam's 2021 Calculus Closet
Project. `RevolutionSoup` is licensed under the MIT License. See [`LICENSE`][license] for more information. 

[`GraphPanel.java`][] was based off of [roooodcastro/GraphPanel.java][roooodcastro] on Gist.

FRQ problems used as examples are from past AP Calculus AB and BC exams. AB problems can be found [here][ab] and the BC 
problems [here][bc] on CollegeBoard's website.

[function]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/Function.java
[functions]: https://github.com/SoupyzInc/RevolutionSoup/tree/main/src/main/java/com/cornycorn/revolutionsoup/functions
[cubed]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/XCubed.java
[problem]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/com/cornycorn/revolutionsoup/problems/Problem.java
[problems]: https://github.com/SoupyzInc/RevolutionSoup/tree/main/src/main/java/com/cornycorn/revolutionsoup/problems
[app]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/com/cornycorn/revolutionsoup/App.java

[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[license]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/LICENSE

[roooodcastro]: https://gist.github.com/roooodcastro/6325153

[ab]: https://apcentral.collegeboard.org/courses/ap-calculus-ab/exam/past-exam-questions
[bc]: https://apcentral.collegeboard.org/courses/ap-calculus-bc/exam/past-exam-questions 
