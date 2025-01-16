# Introduction to Java

## Enumerate and describe basic instruction types for any program
Input, Output, And Process

## Describe computational thinking

## Enumerate the minimal requirements of any Java program
A program starts in main(), executing the statements within main's braces { }, one at a time.
Each statement typically appears alone on a line and ends with a semicolon, as English sentences end with a period.
The int wage statement creates an integer variable named wage. The wage = 20 statement assigns wage with 20.
The print and println statements output various values.

## Describe the Java compilation process

## Demonstrate Java code for getting information from the user
Need to have `import java.util.Scanner;` at the top of program. <br>
Getting input is achieved by first creating a Scanner object via the statement: `Scanner scnr = new Scanner(System.in);`. System.in corresponds to keyboard input. Then, given Scanner object scnr, the following statement gets an input value and assigns x with that value: `x = scnr.nextInt() ;`

## Provide code example 
```
import java.util.Scanner;

public class Salary {
   public static void main(String [] args) {
      int wage;

      Scanner scnr = new Scanner(System.in);
      wage = scnr.nextInt();

      System.out.print("Salary is ");
      System.out.println(wage * 40 * 52);
   }
}
```

## Demonstrate Java code for printing information to the console
*name of class*.out.print() - to print <br>
*name of class*.out.println() - to print on new line

## Provide code example
```
public class Salary {                       

  public static void main (String [] args) {
      int wage;

      wage = 20;                        

      System.out.print("Salary is ");       
      System.out.println(wage * 40 * 52);   
  }
}
```

## Enumerate and describe the different types of errors
Syntax error is to violate a programming language's rules on how symbols can be combined to create a program. An example is forgetting to end a statement with a semicolon. <br>
A logic error, also called a bug, is an error that occurs while a program runs. For example, a programmer might mean to type numBeans * numJars but accidentally types numBeans + numJars (+ instead of *). The program would compile but would not run as intended. <br>

## Describe the difference between errors and warnings
A warning indicates a possible logic error. It doesn't stop the compiler from creating an executable program.

## Describe the purpose of an IDE and enumerate the common features of an IDE
An integrated development environment (IDE) is software that integrates a text editor and a compiler, often with additional tools. Ex: Most IDEs include the ability to edit multiple files, a file manager for finding and organizing files, shortcut buttons, a debugger to help a programmer find bugs, and a console for entering commands and executing programs. <br>
Common Features:
- Syntax highlighting uses different colors for keywords, variables, strings, and other code syntax elements. Syntax highlighting can help a programmer identify errors such as unclosed quotes and parentheses, missing semicolons, and misspellings.
- Automatic delimiter completion adds a matching closing quotation mark, parenthesis, bracket, or brace when a corresponding opening symbol is typed, which can help errors be more easily identified.
- Automatic indentation adds indents to lines after braces, which can make code more readable.
- Shortcut buttons allow various predefined operations to be performed with a single click. Ex: A common shortcut button allows a program to be compiled and run quickly without having to enter a command to compile a program into an executable and then running that executable.
- A file manager and multiple text editor tabs for separate files allow different programs, input files, and output files to be easily accessed and edited.
