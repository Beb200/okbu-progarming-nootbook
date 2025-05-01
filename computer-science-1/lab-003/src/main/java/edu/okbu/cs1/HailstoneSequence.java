package edu.okbu.cs1;

public class HailstoneSequence {
    int theNumber;
    public HailstoneSequence(int theNumber){
        this.theNumber = theNumber;
    }

    public int computeHailstoneSequence(){
        while(theNumber != 1) {
                if (theNumber % 2 == 0){
                    theNumber = theNumber / 2;
                }
                if (theNumber % 2 == 1){
                    theNumber = (theNumber * 3) + 1;
                }
                    
            }
            return theNumber;
        }
                
    public static void main(String[] args) {
        
    }

}
