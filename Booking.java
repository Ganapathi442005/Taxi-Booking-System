
public class Booking {
    int bookingid, customerid, pickuptime, droptime, amount;
    char from, to;

    public Booking(int bookingid, int customerid, char from, char to,
                   int pickuptime, int droptime, int amount) {
        this.bookingid = bookingid;
        this.customerid = customerid;
        this.from = from;
        this.to = to;
        this.pickuptime = pickuptime;
        this.droptime = droptime;
        this.amount = amount;
    }
}
