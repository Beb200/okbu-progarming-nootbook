# Enumerate and describe basic instruction types for any program
Input, Output, And Process
# Describe computational thinking

# Enumerate the minimal requirements of any Java program
A program starts in main(), executing the statements within main's braces { }, one at a time.
Each statement typically appears alone on a line and ends with a semicolon, as English sentences end with a period.
The int wage statement creates an integer variable named wage. The wage = 20 statement assigns wage with 20.
The print and println statements output various values.
# Describe the Java compilation process

# Demonstrate Java code for getting information from the user
need to have ```import java.util.Scanner;``` at the top of program
Getting input is achieved by first creating a Scanner object via the statement: ```Scanner scnr = new Scanner(System.in);```. System.in corresponds to keyboard input. Then, given Scanner object scnr, the following statement gets an input value and assigns x with that value: ```x = scnr.nextInt() ;```
# Provide code example 
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
# Demonstrate Java code for printing information to the console
"name of class".out.print() - to print
"name of class".out.println() - to print on new line
# Provide code example
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
# Enumerate and describe the different types of errors

# Describe the difference between errors and warnings

# Describe the purpose of an IDE and enumerate the common features of an IDE
