import java.util.*;

public class TaxiBookingSystem {

    static List<Taxi> taxis = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int customerCounter = 1;

    public static void main(String[] args) {

        System.out.print("Enter the number of taxis: ");
        int n = sc.nextInt();
        initialiseTaxis(n);

        while (true) {
            System.out.println("\n1. Book Taxi\n2. Display Taxi Details\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> bookTaxi();
                case 2 -> displayTaxiDetails();
                case 3 -> { System.out.println("Exiting…"); return; }
                default -> System.out.println("Invalid choice, try again.");
            }
        } 
    }

    private static void initialiseTaxis(int n) {
        for (int i = 1; i <= n; i++) taxis.add(new Taxi(i));
    }

    private static void bookTaxi() {

        int customerId = customerCounter++;

        System.out.print("Pickup point (A-F): ");
        char pickup = sc.next().toUpperCase().charAt(0);

        System.out.print("Drop point   (A-F): ");
        char drop   = sc.next().toUpperCase().charAt(0);

        System.out.print("Pickup time (hour): ");
        int pickupTime = sc.nextInt();

        Taxi selectedTaxi = null;
        int minDistance   = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            if (!taxi.isavailable(pickupTime)) continue;

            int distance = Math.abs(taxi.currentpoint - pickup);
            if (distance < minDistance ||
               (distance == minDistance &&
                taxi.totalearning < (selectedTaxi != null ?selectedTaxi.totalearning
                                                          : Integer.MAX_VALUE))) {
                selectedTaxi = taxi;
                minDistance  = distance;
            }
        }

        if (selectedTaxi == null) {
            System.out.println("Booking rejected - no taxis free.");
            return;
        }

        int dropTime = pickupTime + Math.abs(drop - pickup);
        int fare     = selectedTaxi.earnings(pickup, drop);
        int bookingId = selectedTaxi.bookings.size() + 1;

        Booking b = new Booking(bookingId, customerId, pickup, drop,
                                pickupTime, dropTime, fare);

        selectedTaxi.addbooking(b);
        System.out.println("Taxi-" + selectedTaxi.id + " is allocated.");
    }

    private static void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            System.out.println("\nTaxi-" + taxi.id + " | Total Earnings: Rs." + taxi.totalearning);

            if (taxi.bookings.isEmpty()) {
                System.out.println("  (no bookings yet)");
                continue;
            }

            System.out.printf(
                    "BookingID", "CustomerID", "From", "To",
                    "PickupTime", "DropTime", "Amount");

            for (Booking b : taxi.bookings) {
                System.out.printf(
                        b.bookingid, b.customerid, b.from, b.to,
                        b.pickuptime, b.droptime, b.amount);
            }
        }
    }
}
