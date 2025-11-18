package edu.okbu.cs1;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class ListAverage {
    Scanner scnr = new Scanner(System.in);
    double theAverage;
    int currValue; //= scnr.nextInt();
    int valueSum = 0;
    int numValues = 0;
    Scanner fileScanner;
    
    public ListAverage(){
        
    }
    public void openFile() throws IOException{
        FileInputStream inFile = new FileInputStream("LAfile.txt");
        fileScanner = new Scanner(inFile);

        currValue = fileScanner.nextInt();
        //valueSum = fileScanner.nextInt();
       // numValues = fileScanner.nextInt();
        
        

    }


    public void computeAverage(int currValue, int valueSum, int numValues) throws FileNotFoundException{
        FileOutputStream myFile = new FileOutputStream("LAoutFile.txt");
        PrintWriter myOut = new PrintWriter(myFile);
        while (currValue > 0) {
            valueSum = valueSum + currValue;
            numValues = numValues + 1;
            currValue = fileScanner.nextInt(); //scnr.nextInt();
        }
        myOut.println("Average: " + (valueSum / numValues));
        fileScanner.close();
        myOut.close();
    }
        
    public static void main(String[] args) throws FileNotFoundException {
        ListAverage l = new ListAverage();
        l.computeAverage(10,0,0);
    }
}
