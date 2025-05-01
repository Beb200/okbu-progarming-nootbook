package okbu.cs1;

public class Toll {
    int timeHour;
    int timeMinute;
    int typeOfDay;
    int numPeople;
    double tollAmount;

    public Toll(int timeHour, int timeMinute, int typeOfDay, int numPeople){
        this.timeHour = timeHour;
        this.timeMinute = timeMinute;
        this.typeOfDay = typeOfDay;
        this.numPeople = numPeople; 
    }

    public double getTollAmount() {
        if (typeOfDay == 0){
            if (timeHour < 6){
                return 1.55;
            }
            else if (timeHour < 10){
                return 4.65;
            }
            else if (timeHour < 18){
                return 2.35;
            }
            else {
                return 1.55;
            }
        }
        else {
            if (timeHour < 8){
                return 1.55;
            }
            else if (timeHour < 12){
                return 3.05;
            }
            else if (timeHour < 16){
                return 3.45;
            }
            else if (timeHour < 19){
                return 3.60;
            }
            else if (timeHour < 22){
                return 3.05;
            }
            else {
                return  1.55;
            }
        }/*
        else if (numPeople >= 3){
            if ((typeOfDay == 0) && (timeHour >= 6) && (timeHour < 10)){
                return (getTollAmount() * 0.5);
            }
            else {
                return getTollAmount();
            }
        }*/
    }

    
}
