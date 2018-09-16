package no.hvl.dat159;

        import java.util.Iterator;
        import java.util.ArrayList;

public class Customer {
    private String _name;
    private ArrayList<Rental> _rentals = new ArrayList<>();


    public Customer(String name) {

        _name = name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = _rentals.iterator();
        String result = "videostore.Rental Record for " + getName() + "\n";
        while (rentals.hasNext()) {

            Rental each = rentals.next();
            int daysRented = each.getDaysRented();
            Movie movie = each.getMovie();
            double thisAmount = movie.determineAmount(daysRented);

            int priceCode = movie.getPriceCode();
            String title = movie.getTitle();

            frequentRenterPoints++;

            frequentRenterPoints += movie.getFrequentRenterPoints(frequentRenterPoints, priceCode, daysRented);




            result = getFiguresForRental(result, thisAmount, title);
            totalAmount += thisAmount;
        }

        return getFooterLines(totalAmount, frequentRenterPoints, result);
    }

    private String getFiguresForRental(String result, double thisAmount, String title) {
        return result + ("\t" + thisAmount + "\t" + title + "\n");

    }

    private String getFooterLines(double totalAmount, int frequentRenterPoints, String result) {
        return result
                + "Amount owed is " + String.valueOf(totalAmount) + "\n"
                + "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";

    }

    public void addRental(Rental arg) {

        _rentals.add(arg);
    }

    private String getName() {

        return _name;
    }
}
