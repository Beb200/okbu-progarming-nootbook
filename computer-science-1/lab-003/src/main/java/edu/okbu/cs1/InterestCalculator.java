package edu.okbu.cs1;

public class InterestCalculator {
    double initailSavings;
    double interestRate;
    double currentSavings;

    public void computeSavings(double currSavings, double initialSavings, double i, double interestRate){
        currSavings = initialSavings;
      for (i = 0; i < 10; ++i) {
         System.out.println("$" + currSavings);
         currSavings = currSavings + (currSavings * interestRate);
      }
    }

    public static void main(String[] args) {
        InterestCalculator i = new InterestCalculator();
        i.computeSavings(10000,10000,0,0.05);
    }
}
