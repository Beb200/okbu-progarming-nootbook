package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayPractice {
    int[] theNumbers = new int[20];
    int i;
    Scanner scanFile;
    int count;

    public void initalize(String inFile, String outFile) throws FileNotFoundException{
        FileInputStream inFile1 = new FileInputStream(inFile);
        scanFile = new Scanner(inFile1);

        theNumbers[0] = 1;

        for (i = 0; i < 20; i++){
            theNumbers[i] = scanFile.nextInt();
        }
        /*for (i = 0; i < 20; i++){
            System.out.println(theNumbers[i]);
        }*/
    }

    public void cleanup(){

    }

    public void reverse(){
        System.out.println(theNumbers[19]);
        System.out.println(theNumbers[18]);
        System.out.println(theNumbers[17]);
        System.out.println(theNumbers[16]);
        System.out.println(theNumbers[15]);
        System.out.println(theNumbers[14]);
        System.out.println(theNumbers[13]);
        System.out.println(theNumbers[12]);
        System.out.println(theNumbers[11]);
        System.out.println(theNumbers[10]);
        System.out.println(theNumbers[9]);
        System.out.println(theNumbers[8]);
        System.out.println(theNumbers[7]);
        System.out.println(theNumbers[6]);
        System.out.println(theNumbers[5]);
        System.out.println(theNumbers[4]);
        System.out.println(theNumbers[3]);
        System.out.println(theNumbers[2]);
        System.out.println(theNumbers[1]);
        System.out.println(theNumbers[0]);

    }


    public int findMin(){
        int min;
        min = theNumbers[0];
        for(i = 0; i < theNumbers.length; i++){
            if(theNumbers[i] < min){
                min = theNumbers[i];
            }
        }
        return min;
    }

    public int findMax(){
        int max;
        max = theNumbers[0];
        for(i = 0; i < theNumbers.length; i++){
            if(theNumbers[i] > max){
                max = theNumbers[i];
            }
        }
        return max;
    }

    public float findAverage(){
        float average = 0;
        for (i = 0; i < theNumbers.length; i++){
        average = theNumbers[i] + average;
        }
        return average;
    }

    public int countLessThan(){
        
        Scanner scnr = new Scanner(System.in);
        int argument = scnr.nextInt();
        for(i = 0; i < theNumbers.length; i++){
            if (theNumbers[i] < argument){
                count =+ 1;
            }
        }
        
        return count;
    }
}
