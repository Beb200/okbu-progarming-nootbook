package okbu.cs1;

public class IncomeTaxForm {
    final double TEN_PCT;
    final double TWELVE_PCT;
    final double TWENTY_TWO_PCT;
    final double TWENTY_FOUR_PCT;
    int wages;
    int interest;
    int unemplyment;
    int status;
    int withheld;
    
    public void setWages(int wages) {
        this.wages = wages;
    }
    public void setInterest(int interest) {
        this.interest = interest;
    }
    public void setUnemplyment(int unemplyment) {
        this.unemplyment = unemplyment;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setWithheld(int withheld) {
        this.withheld = withheld;
    }
    
    
}
