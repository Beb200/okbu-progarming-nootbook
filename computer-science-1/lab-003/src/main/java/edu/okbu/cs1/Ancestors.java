package edu.okbu.cs1;

public class Ancestors {
    int userYear;
    int consYear;
    int numAnc;
/*
    public int Ancestors(int year, int cons, int num){
       this.userYear = year;
       this.consYear = cons;
       this.numAnc = num;
    }*/

    public void printInformation(int userYear, int consYear, int numAnc){
        System.out.println("Enter a past year (neg. for B.C.): ");
        while (consYear >= userYear){
            System.out.println("Ancestors in " + consYear + ": " + numAnc);
            numAnc = 2 * numAnc;
            consYear = consYear - 20;
        }
    }

    
    public static void main(String[] args) {
        Ancestors a = new Ancestors();
        a.printInformation(1600,2020,2);
    }
}
