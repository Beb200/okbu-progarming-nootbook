package edu.okbu;

public class CaffeineLevels {
    double caffeineMg;

    public CaffeineLevels(double caffeineMg){
        this.caffeineMg = caffeineMg;
    }

    public double afterSix(){
        return caffeineMg / 2 ;
    }

    public double afterTwelve(){
        return afterSix() / 2;
    }

    public double afterTwentyFour(){
        return afterTwelve() / 2;
    }
}
