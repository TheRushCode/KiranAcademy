import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract base class: defines the common discount contract.
abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountPercentage();

    public String getPassengerType() {
        return "Passenger";
    }
}

class RegularPassenger extends Passenger {
    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}

class StudentPassenger extends Passenger {
    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 10.0;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}

class Flight {
    private String code;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String code, String airline, String source, String destination,
                  int availableSeats, double baseFare) {
        this.code = code;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getCode() {
        return code;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasEnoughSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void reserveSeats(int tickets) {
        // Seat counts are changed only after all booking validations pass.
        if (hasEnoughSeats(tickets)) {
            availableSeats -= tickets;
        }
    }

    public void display() {
        System.out.printf("%-8s %-15s %-14s %-14s %-8d Rs.%.2f%n",
                code, airline, source, destination, availableSeats, baseFare);
    }
}

class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int ticketCount;
    private double subtotal;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight, Passenger passenger, int ticketCount) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.ticketCount = ticketCount;

        this.subtotal = flight.getBaseFare() * ticketCount;
        this.discount = subtotal * passenger.getDiscountPercentage() / 100.0;
        this.totalFare = subtotal - discount;
    }

    public void displaySummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight: " + flight.getCode() + " | " + flight.getSource()
                + " to " + flight.getDestination());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Passenger type: " + passenger.getPassengerType());
        System.out.println("Tickets: " + ticketCount);
        System.out.printf("Base fare per ticket: Rs.%.2f%n", flight.getBaseFare());
        System.out.printf("Subtotal: Rs.%.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%): Rs.%.2f%n",
                passenger.getDiscountPercentage(), discount);
        System.out.printf("Final amount: Rs.%.2f%n", totalFare);
        System.out.println("=====================================");
    }
}

class FlightBookingSystem {
    private List<Flight> flights = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int nextBookingNumber = 1001;

    public FlightBookingSystem() {
        flights.add(new Flight("PN101", "Kiran Air", "Pune", "Delhi", 20, 4000));
        flights.add(new Flight("MB202", "Sky India", "Mumbai", "Bengaluru", 25, 3500));
        flights.add(new Flight("PN303", "Air Bharat", "Pune", "Hyderabad", 18, 3000));
    }

    public void displayFlights() {
        System.out.println("\n================ AVAILABLE FLIGHTS ================");
        System.out.printf("%-8s %-15s %-14s %-14s %-8s %s%n",
                "Code", "Airline", "Source", "Destination", "Seats", "Base Fare");
        for (Flight flight : flights) {
            flight.display();
        }
        System.out.println("===================================================");
    }

    public Flight findFlightByCode(String code) {
        for (Flight flight : flights) {
            if (flight.getCode().equalsIgnoreCase(code.trim())) {
                return flight;
            }
        }
        return null;
    }

    public boolean validateBookingDetails(Flight flight, String name, int age, int tickets) {
        if (flight == null) {
            System.out.println("Error: Flight code does not exist.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Passenger name cannot be blank.");
            return false;
        }
        if (age <= 0) {
            System.out.println("Error: Age must be greater than 0.");
            return false;
        }
        if (tickets <= 0) {
            System.out.println("Error: Number of tickets must be greater than 0.");
            return false;
        }
        if (!flight.hasEnoughSeats(tickets)) {
            System.out.println("Booking failed: Not enough seats available.");
            return false;
        }
        return true;
    }

    public void bookFlight(Scanner scanner) {
        System.out.print("Enter flight code: ");
        String code = scanner.nextLine().trim();
        Flight flight = findFlightByCode(code);

        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine().trim();

        int age = readInteger(scanner, "Enter age: ");
        if (age == Integer.MIN_VALUE) {
            System.out.println("Error: Age must be a valid integer.");
            return;
        }

        System.out.print("Passenger type (Regular/Student): ");
        String type = scanner.nextLine().trim();

        int tickets = readInteger(scanner, "Enter number of tickets: ");
        if (tickets == Integer.MIN_VALUE) {
            System.out.println("Error: Ticket count must be a valid integer.");
            return;
        }

        if (!type.equalsIgnoreCase("Regular") && !type.equalsIgnoreCase("Student")) {
            System.out.println("Error: Passenger type must be Regular or Student.");
            return;
        }

        if (!validateBookingDetails(flight, name, age, tickets)) {
            return;
        }

        Passenger passenger;
        if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else {
            passenger = new RegularPassenger(name, age);
        }

        // Confirm only after every validation succeeds.
        String bookingId = "BK" + nextBookingNumber;
        Booking booking = new Booking(bookingId, flight, passenger, tickets);
        flight.reserveSeats(tickets);
        bookings.add(booking);
        nextBookingNumber++;

        System.out.println("\nBooking confirmed!");
        booking.displaySummary();
    }

    private int readInteger(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    public void viewLatestBooking() {
        if (bookings.isEmpty()) {
            System.out.println("No confirmed booking exists yet.");
            return;
        }
        bookings.get(bookings.size() - 1).displaySummary();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Kiran Air Booking");
        while (running) {
            System.out.println("\n1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayFlights();
                    break;
                case "2":
                    bookFlight(scanner);
                    break;
                case "3":
                    viewLatestBooking();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using Kiran Air Booking!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }
}

public class FlightBookingApp {
    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.run();
    }
}

