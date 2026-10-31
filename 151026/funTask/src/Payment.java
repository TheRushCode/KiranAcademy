public class Payment {
    public static void main(String[] args) {

        System.out.println("Hello, World!");

        double balance = 5000 ;

        int amount = 6000;


        if(amount > balance ) {
            System.out.println("insufficient Balance ");
        }
        else {
            System.out.println("Payment Success");
        }

    }
}
