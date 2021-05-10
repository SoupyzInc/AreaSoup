# RevolutionSoup
A Java Swing app that gives a guided walk through on volume of revolution problems
from the Type 4 FRQs packet.

### Usage
`RevolutionSoup` is a basic calculus engine that can integrate a function and solve
volume of revolution problems, granted that you can interpret the word problem and
feed `RevolutionSoup` the right data.

### How to add new problems
#### Making new functions
To add new functions, create a new class that `implements` the `Function` interface.
Function is defined under 
[src/main/java/Function.java](https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/Function.java) as
```java
public interface Function {
    double f(double x);
}
```
An example of a cubic function is defined under 
[src/main/java/XCubed.java](https://github.com/SoupyzInc/RevolutionSoup/blob/main/src/main/java/XCubed.java) as
```java
public class XCubed implements Function {
    public double f(double x) {
        return Math.pow(x, 3);
    }
}
```

#### Calculating Volume of Revolutions
```java
Function topFunction = new xCubed(); // f(x) = x^3
FUnction bottomFunction = new xSquared(); // f(x) = x^2
        
RevolutionSoup rs = new RevolutionSoup(topFunction, bottomFunction, 10000000);

//Revolve from 0 to 1, about y = 3.
System.out.println(rs.revolution(0, 1, 3));
```

### Attribution
This project was made by [Matthew Okashita](https://github.com/SoupyzInc) for 
Mr. Adam's 2021 Calculus Closet Project.
