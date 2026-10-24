
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Show implements Bookable {
    private String movieName;
    private Set<String> bookedSeats = new HashSet<>();

    private String[] allSeats = {
        "A1", "A2", "A3", "A4", "A5",
        "B1", "B2", "B3", "B4", "B5",
        "C1", "C2", "C3", "C4", "C5"
    };

    public Show(String movieName) {
        this.movieName = movieName;
    }

    public void displaySeats() {
        System.out.println("\n========== CINEBOOK ==========");
        System.out.println("Movie: " + movieName);
        System.out.println("             SCREEN");
        System.out.println("------------------------------");

        for (int i = 0; i < allSeats.length; i++) {
            String seat = allSeats[i];

            if (bookedSeats.contains(seat)) {
                System.out.print("[XX] ");
            } else {
                System.out.print("[" + seat + "] ");
            }

            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println("------------------------------");
        System.out.println("[Seat] = Available");
        System.out.println("[XX]   = Booked");
    }

    @Override
    public boolean bookSeats(String[] seatCodes) {
        Set<String> requestedSeats = new HashSet<>();

        for (String seat : seatCodes) {
            seat = seat.toUpperCase();

            boolean exists = false;

            for (String availableSeat : allSeats) {
                if (availableSeat.equals(seat)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                System.out.println("Invalid seat code: " + seat);
                return false;
            }

            if (bookedSeats.contains(seat)) {
                System.out.println("Seat already booked: " + seat);
                return false;
            }

            if (!requestedSeats.add(seat)) {
                System.out.println("Duplicate seat code: " + seat);
                return false;
            }
        }

        for (String seat : requestedSeats) {
            bookedSeats.add(seat);
        }

        System.out.println("Booking successful!");

        return true;
    }

    @Override
    public void cancelBooking(String[] seatCodes) {
        for (String seat : seatCodes) {
            seat = seat.toUpperCase();

            if (bookedSeats.remove(seat)) {
                System.out.println("Booking cancelled: " + seat);
            } else {
                System.out.println("Seat is not booked: " + seat);
            }
        }
    }

    public void displayBookedSeats() {
        System.out.println("\nBooked Seats: " + bookedSeats);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter movie name: ");
        String movie = sc.nextLine();

        Show show = new Show(movie);

        while (true) {
            System.out.println("\n1. View Seats");
            System.out.println("2. Book Seats");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booked Seats");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                show.displaySeats();

            } else if (choice.equals("2")) {
                show.displaySeats();

                System.out.print(
                    "Enter seat codes separated by spaces: "
                );

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Please enter seat codes.");
                    continue;
                }

                String[] seats = input.split("\\s+");

                System.out.println("\nSeat Types:");
                System.out.println("1. REGULAR - Rs.150");
                System.out.println("2. PREMIUM - Rs.250");
                System.out.println("3. VIP - Rs.400");

                System.out.print("Choose seat type: ");
                String type = sc.nextLine().trim();

                SeatType seatType;

                if (type.equals("1")) {
                    seatType = SeatType.REGULAR;
                } else if (type.equals("2")) {
                    seatType = SeatType.PREMIUM;
                } else if (type.equals("3")) {
                    seatType = SeatType.VIP;
                } else {
                    System.out.println("Invalid seat type.");
                    continue;
                }

                if (show.bookSeats(seats)) {
                    int total = seats.length * seatType.getPrice();

                    System.out.println("Seat Type: " + seatType);
                    System.out.println(
                        "Number of Seats: " + seats.length
                    );
                    System.out.println(
                        "Price per Seat: Rs." + seatType.getPrice()
                    );
                    System.out.println("Total Amount: Rs." + total);
                }

            } else if (choice.equals("3")) {
                show.displaySeats();

                System.out.print(
                    "Enter seat codes to cancel: "
                );

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Please enter seat codes.");
                    continue;
                }

                String[] seats = input.split("\\s+");
                show.cancelBooking(seats);

            } else if (choice.equals("4")) {
                show.displayBookedSeats();

            } else if (choice.equals("5")) {
                System.out.println("Thank you for using CineBook!");
                break;

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
