package okbu.cs1;

public class Insurance {
    int userAge;
    double insurancePrice;

    public Insurance(int userAge, double insurancePrice){
        this.userAge = userAge;
        this.insurancePrice = insurancePrice;
    }

    public double getPrice(){
        if (userAge < 16){
            return insurancePrice = 0;
        }
        else if (userAge < 25){
            return insurancePrice = 4800;
        }
        else if (userAge < 40){
            return insurancePrice = 2350;
        }
        else {
            return insurancePrice = 2100;
        }
    }
}
