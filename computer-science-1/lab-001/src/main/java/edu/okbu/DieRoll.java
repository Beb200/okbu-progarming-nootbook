package edu.okbu;
import java.util.Random;

public class DieRoll { 
    int minPip;
    int maxpip;
    Random random = new Random();

    public DieRoll(int minPip, int maxpip){
        this.minPip = minPip;
        this.maxpip = maxpip;
    }

    public int Roll(){
        return random.nextInt(maxpip - minPip + 1) + minPip;
    }
}
