
import java.util.*;

public class Taxi {
    int id;
    int totalearning = 0;
    int currentpoint = 'A';
    List<Booking> bookings = new ArrayList<>();

    public Taxi(int id) {
        this.id = id;
    }

    public boolean isavailable(int requesttime) {
        if (bookings.isEmpty()) {
            return true;
        }
        Booking lastbooking = bookings.get(bookings.size() - 1);
        return lastbooking.droptime <= requesttime;
    }

    public int earnings(char from, char to) {
        int distance = Math.abs((to - from) * 15);
        return 100 + Math.max(0, distance - 5) * 10;
    }

    public void addbooking(Booking booking) {
        bookings.add(booking);
        totalearning += booking.amount;
        currentpoint = booking.to;
    }
}
