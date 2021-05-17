# AreaSoup
A Java Swing app to visualize different integral approximation methods and perform basic calculus operations.

### Adding new functions
To add new functions, create a new class that `implements` the `Function` interface. `Function` is defined under 
[Function.java][function] as
```java
public interface Function {
    double f(double x);
}
```

An example implementation is provided below.

```java
public class YourFunction implements Function {
    public double f(double x) {
        return /*The y-value that your function should return for x.*/;
    }
}
```

More examples can be found in the [functions folder][functions].

To add new functions to the GUI, find the `addFunctions` method in [`GraphPanel.java`][graph] and add a new `JMenuItem`
to `JMenu fm` like so.

```java
JMenuItem yourFunctionItem = new JMenuItem("Your Function");
yourFunctionItem.addActionListener(ev -> {
    function = new YourFunction();
    functionName = "Your Function";
    setValues();
});
fm.add(yourFunctionItem);
```

The rest of the code should handle everything else for you.

### Attribution
This project was made by [Matthew Okashita][soupyzinc] and [Joseph Benigno][jojongx] for Mr. Adam's 2021 Calculus Closet
Project. `AreaSoup` is licensed under the MIT License. See [`LICENSE`][license] for more information. 

The graphing engine in [`GraphPanel.java`][graph] was based off of [roooodcastro/GraphPanel.java][roooodcastro] on Gist.

[function]: https://github.com/SoupyzInc/AreaSoup/blob/main/src/main/java/Function.java
[functions]: https://github.com/SoupyzInc/AreaSoup/tree/main/src/main/java/com/cornycorn/AreaSoup/functions
[problem]: https://github.com/SoupyzInc/AreaSoup/blob/main/src/main/java/com/cornycorn/AreaSoup/problems/Problem.java
[problems]: https://github.com/SoupyzInc/AreaSoup/tree/main/src/main/java/com/cornycorn/AreaSoup/problems
[app]: https://github.com/SoupyzInc/AreaSoup/blob/main/src/main/java/com/cornycorn/AreaSoup/App.java

[soupyzinc]: https://github.com/SoupyzInc
[jojongx]: https://github.com/jojongx
[license]: https://github.com/SoupyzInc/AreaSoup/blob/main/LICENSE

[graph]: https://github.com/SoupyzInc/AreaSoup/blob/main/src/main/java/com/cornycorn/AreaSoup/GraphPanel.java
[roooodcastro]: https://gist.github.com/roooodcastro/6325153

[ab]: https://apcentral.collegeboard.org/courses/ap-calculus-ab/exam/past-exam-questions
[bc]: https://apcentral.collegeboard.org/courses/ap-calculus-bc/exam/past-exam-questions 
