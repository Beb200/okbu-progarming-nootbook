package edu.okbu.cs1;

public class Temperature {
    public Temperature(){

    }

    public void cToF(double min, double max ){
        while (min < max) {
            System.out.println(((min * 9.0) / 5.0) + 32.0); 
            min = min + 5;
        }
    }

    public void fToC(double min, double max){
        while (min < max) {
            System.out.println((min - 32)* (5.0/9.0));
            min = min + 5; 
        }
    }

    public static void main(String[] args) {
        Temperature t = new Temperature();
        t.cToF(20, 40);
        t.fToC(20,40);
    }
}
