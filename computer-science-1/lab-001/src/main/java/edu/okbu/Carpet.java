package edu.okbu;

public class Carpet {
    double price;
    int width;
    int length;
    final double TAX_RATE = 0.07;
    final double WASTE_PCT = 1.20;
    final double LABOR_RATE = 0.75;

    public Carpet(double price, int width, int length){
        this.price = price;
        this.width = width;
        this.length = length;
    }

    public int SquareFootage(){
        return width * length;
    }

    public double CarpetCost(){
        return price * (SquareFootage() * WASTE_PCT);
    }

    public double LaborCost(){
        return LABOR_RATE * SquareFootage();
    }

    public double Tax(){
        return TAX_RATE * (CarpetCost() + LaborCost());
    }

    public double TotalCost(){
        return Tax() + LaborCost() + CarpetCost();
    }
}
