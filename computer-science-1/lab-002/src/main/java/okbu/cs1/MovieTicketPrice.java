package okbu.cs1;

public class MovieTicketPrice {
    int userAge;
    int movieTicketPrice;

    public MovieTicketPrice(int userAge, int movieTicketPrice){
        this.userAge = userAge;
        this.movieTicketPrice = movieTicketPrice;
    }

    public int getMovieTicketPrice() {
        if (userAge <= 12){
            return 11;            
        }
        else if (userAge >= 65){
            return 12;
        }
        else {
            return 14;
        }
    }

    
}
