package okbu.cs1;

public class ExactChange {
    int numPennies;
    int NumDollars;
    int numDimes;
    int numNickels;
    int numQuarters;
    int totalChange;

    ExactChange(int totalChange){
        this.totalChange = totalChange;
    }

    public int getNumDollars() {
        return NumDollars;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public int getNumPennies() {
        return numPennies;
    }   
}
