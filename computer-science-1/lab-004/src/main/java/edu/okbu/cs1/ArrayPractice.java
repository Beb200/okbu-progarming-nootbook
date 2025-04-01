package edu.okbu.cs1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayPractice {
    int[] theNumbers;
    int i;
    Scanner scanFile;

    public void initalize(FileInputStream inFile, String outFile) throws FileNotFoundException{
        inFile = new FileInputStream("inFile");
        scanFile = new Scanner(inFile);

        for (i = 0; i < 20; i++){
            theNumbers[i] = scanFile.nextInt();
        }
        for (i = 0; i < 20; i++){
            System.out.println(theNumbers[i]);
        }
    }

    public void cleanup(){

    }

    public void reverse(){

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
        return 1.1f;
    }

    public int countLessThan(){
        return 1;
    }
}
