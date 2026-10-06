// 2. create 2 variables cost_price , selling_price and calculate the profit or loss


public class Main {
    public static void main(String[] args) {

        int costPrice = 500;
        int sellingPrice = 600;

        if (sellingPrice > costPrice)
            System.out.println("Profit = " + (sellingPrice - costPrice));
        else if (cost_price > selling_price)
            System.out.println("Loss = " + (costPrice - sellingPrice));
        else
            System.out.println("No Profit No Loss");
    }
}
