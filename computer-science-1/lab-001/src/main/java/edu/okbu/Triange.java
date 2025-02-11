package edu.okbu;

public class Triange {
    double side1L;
    double side2L;
    double side3L;

    public Triange(double side1L, double side2L, double side3L){
        this.side1L = side1L;
        this.side2L = side2L;
        this.side3L = side3L;
    }

    public double Perimeter(){
        return side1L + side2L + side3L;
    }

    public double Area(){
        return Math.sqrt(side1L * side2L * side3L);
    }
}
