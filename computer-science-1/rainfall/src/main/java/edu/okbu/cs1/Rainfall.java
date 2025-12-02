package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rainfall {
    FileInputStream fileIn = null;
    Scanner inFile = null;
    Scanner scnr = new Scanner(System.in);
    FileOutputStream fileStream = null;
    PrintWriter outFS = null;
    int i;

    String name;
    int rainfall[] = new int[30];
    int average = 0;
    int add = 0;
    int min;
    int max;
    int day;

    public void Main() throws FileNotFoundException{
        fileIn = new FileInputStream("infFile.txt");
        inFile = new Scanner(fileIn);

        name = inFile.next();

        for (i = 0; i < rainfall.length; i++){
            rainfall[i] = inFile.nextInt();
            if (rainfall[i] < 0){
                rainfall[i] = 0;
            }
            else if (rainfall[i] > 10){
                rainfall[i] = 0;
            }
            
        }
    }

    public void average(){
        for (i = 0; i < rainfall.length; i++){
            add = rainfall[i] + add;
        }
        average = add / rainfall.length;
    }

    public void min(){
        min = rainfall[0];
        for (i = 0; i < rainfall.length; i++){
            if (min > rainfall[i]){
                min = rainfall[i];
            }
        }
    }

    public void max(){
        max = rainfall[0];
        for (i = 0; i < rainfall.length; i++){
            if (max < rainfall[i]){
                max = rainfall[i];
            }
        }
    }

    public void Tostring(){
        System.out.printf("Name: %s\n",name);
        System.out.printf("Averge: %s\n", average);
        System.out.printf("Min: %s\n", min);
        System.out.printf("Max: %s\n", max);
    }

    public void getRainFallOnDay(){
        System.out.print("Check rainfall on day ");
        day = scnr.nextInt();
        System.out.println(rainfall[day]);
    }

    public void Print(){
        System.out.println(name);
        for (i = 0; i < rainfall.length; i++){
            System.out.println(rainfall[i]);
        }
    }

    public void fileOut() throws FileNotFoundException{
        fileStream = new FileOutputStream("outFile.txt");
        outFS = new PrintWriter(fileStream);

        outFS.printf("Name: %s\n",name);
        outFS.printf("Averge: %s\n",average);
        outFS.printf("Min: %s\n",min);
        outFS.printf("Max: %s\n",max);
        for (i = 0; i < rainfall.length; i++){
            outFS.printf("Day %d: %s\n", (i + 1), rainfall[i]);
        }

        outFS.close();
    }

}
