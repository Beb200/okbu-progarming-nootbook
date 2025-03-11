package edu.okbu.cs1;
import java.util.Scanner;

public class ListAverage {
    Scanner scnr = new Scanner(System.in);
    double theAverage;
    int currValue = scnr.nextInt();
    int valueSum  = 0;
    int numValues = 0;
    
    public ListAverage(){
        
    }

    public void computeAverage(int currValue, int valueSum, int numValues){
        while (currValue > 0) {
            valueSum = valueSum + currValue;
            numValues = numValues + 1;
            currValue = scnr.nextInt();
        }
        System.out.println("Average: " + (valueSum / numValues));
    }
        
    public static void main(String[] args) {
        ListAverage l = new ListAverage();
        l.computeAverage(10,0,0);
    }
}
