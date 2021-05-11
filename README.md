# RevolutionSoup
Ultimately a Java app that gives a guided walk through on volume of revolution problems
from the Type 4 FRQs packet; `RevolutionSoup` is a basic calculus engine that can integrate functions and solve
volume of revolution problems.

### How to add new functions
To add new functions, create a new class that `implements` the `Function` interface.
Function is defined under 
[src/main/java/Function.java][function] as
```java
public interface Function {
    double f(double x);
}
```
An example of a cubic function is defined under 
[src/main/java/XCubed.java][cubed] as
```java
public class XCubed implements Function {
    public double f(double x) {
        return Math.pow(x, 3);
    }
}
```

### Example Usage
```java
Function topFunction = new xCubed(); // f(x) = x^3
FUnction bottomFunction = new xSquared(); // f(x) = x^2
        
RevolutionSoup rs = new RevolutionSoup(topFunction, bottomFunction);

// Revolve from 0 to 1, about y = 3.
System.out.println(rs.revolution(0, 1, 3));
```

### Attribution
This project was made by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] for 
Mr. Adam's 2021 Calculus Closet Project.


[function]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/Function.java
[cubed]: https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/XCubed.java
[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx