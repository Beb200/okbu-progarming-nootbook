package edu.okbu.cs1;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Scanner;

public class list {
    Scanner fileScanner;
    
    public void openFile() throws IOException {
        FileInputStream inFile = new FileInputStream("myFile.txt");
        fileScanner = new Scanner(inFile);

        /*
        System.out.println(fileScanner.nextInt());

        System.out.println(fileScanner.nextInt());
*/
    
       // inFile.close();

    }

    public void readData(){
        while (fileScanner.hasNext()) {

            System.out.println(fileScanner.nextLine());
        } 
    }
    
    public static void main(String[] args) throws IOException {

        list d1 = new list();

        d1.readData();   
        d1.openFile();
    }
}