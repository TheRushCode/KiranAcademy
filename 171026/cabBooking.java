
import java.util.Scanner;

public class CabBookingChatbot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book a Cab");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\nAvailable Routes:");
                System.out.println("1. Pune to Mumbai - 150 km");
                System.out.println("2. Pune to Nashik - 210 km");
                System.out.println("3. Pune to Satara - 110 km");

                System.out.print("Select route: ");
                int route = sc.nextInt();

                System.out.println("\nSelect Cab Type:");
                System.out.println("1. Mini - Rs. 10/km");
                System.out.println("2. Sedan - Rs. 15/km");
                System.out.println("3. SUV - Rs. 20/km");

                System.out.print("Enter cab type: ");
                int cab = sc.nextInt();

                int distance = 0;
                int rate = 0;

                if (route == 1) {
                    distance = 150;
                } else if (route == 2) {
                    distance = 210;
                } else if (route == 3) {
                    distance = 110;
                } else {
                    System.out.println("Invalid route!");
                    continue;
                }

                if (cab == 1) {
                    rate = 10;
                } else if (cab == 2) {
                    rate = 15;
                } else if (cab == 3) {
                    rate = 20;
                } else {
                    System.out.println("Invalid cab type!");
                    continue;
                }

                int fare = distance * rate;

                System.out.println("\nBooking Successful!");
                System.out.println("Distance: " + distance + " km");
                System.out.println("Total Fare: Rs. " + fare);

                int otp = 1234;
                System.out.println("Your OTP is: " + otp);

            } else if (choice == 2) {
                System.out.print("Enter OTP to cancel booking: ");
                int enteredOtp = sc.nextInt();

                if (enteredOtp == 1234) {
                    System.out.println("Booking cancelled successfully!");
                } else {
                    System.out.println("Invalid OTP! Cancellation failed.");
                }

            } else if (choice == 3) {
                System.out.println("Thank you for using our Cab Booking Chatbot!");
                break;

            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
