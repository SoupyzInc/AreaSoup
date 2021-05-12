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

### How to add new functions
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

### Attribution
This project was made by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] for Mr. Adam's 2021 Calculus Closet
Project. `RevolutionSoup` is licensed under the MIT License. See [`LICENSE`][license] for more information. 

FRQ problems used as examples are from past AP Calculus AB and BC exams. AB problems can be found [here][ab] and the BC 
problems [here][bc] on CollegeBoard's website.

[function]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/Function.java
[cubed]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/XCubed.java
[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[license]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/LICENSE
[ab]: https://apcentral.collegeboard.org/courses/ap-calculus-ab/exam/past-exam-questions
[bc]: https://apcentral.collegeboard.org/courses/ap-calculus-bc/exam/past-exam-questions 
