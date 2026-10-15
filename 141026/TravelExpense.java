
public class TravelExpense {

    public static double calculateTravelCost(double distance) {
        return distance * 12;
    }

    public static double calculateHotelCost(int days) {
        return days * 1500;
    }

    public static double calculateTotalExpense(double distance, int days) {
        double travel = calculateTravelCost(distance);
        double hotel = calculateHotelCost(days);

        return travel + hotel;
    }

    public static void main(String[] args) {

        double distance = 100;
        int days = 2;

        double travel = calculateTravelCost(distance);
        double hotel = calculateHotelCost(days);
        double total = calculateTotalExpense(distance, days);

        System.out.println("Travel Cost = Rs. " + travel);
        System.out.println("Hotel Cost = Rs. " + hotel);
        System.out.println("Total Expense = Rs. " + total);
    }
}
