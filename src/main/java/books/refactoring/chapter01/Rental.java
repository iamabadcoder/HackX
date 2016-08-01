package books.refactoring.chapter01;

/**
 * Created by hackx on 7/31/16.
 */
public class Rental {

    private Movie movie;
    private int dayRented;

    public Rental(Movie movie, int dayRented) {
        this.movie = movie;
        this.dayRented = dayRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDayRented() {
        return dayRented;
    }

    public void setDayRented(int dayRented) {
        this.dayRented = dayRented;
    }

    double getCharge() {
        return movie.price.getCharge(dayRented, movie);
    }

    int getFrequentRenterPoints(int daysRented) {
        return movie.getFrequentRenterPoints(daysRented);
    }
}
