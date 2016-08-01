package books.refactoring.chapter01;

/**
 * Created by hackx on 8/1/16.
 */
abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
