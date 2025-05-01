package okbu.cs1;

public class Year {
    int theYear;

    public  Year(int theYear){
        this.theYear = theYear;
    }

    public boolean isLeapYear(){

    }

    public int getTheYear() {
        if (year % 4 ==0){
            if ((year % 400 != 0) && (year % 100 == 0)){
                return 0;
            }
            return 1;
        }
    }

    public void setTheYear(int theYear) {
        this.theYear = theYear;
    }
    
}
