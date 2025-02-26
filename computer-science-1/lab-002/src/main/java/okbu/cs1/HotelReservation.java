package okbu.cs1;

public class HotelReservation {
    int numYears;
    double hotelRate;

    public HotelReservation(){
        hotelRate = 150.0;
    }

    public int getNumYears() {
        return numYears;
    }

    public double getHotelRate() {
        if (numYears == 50){
            hotelRate =  hotelRate / 2;
        }
        return hotelRate;
    }

    public void setNumYears(int numYears) {
        this.numYears = numYears;
    }

    public void setHotelRate(double hotelRate) {
        this.hotelRate = hotelRate;
    }



    
}
