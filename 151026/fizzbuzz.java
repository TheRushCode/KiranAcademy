import java.util.*;
public class FizzBuzz {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter a number: ");
int num = sc.nextInt();
if (num % 3 == 0 && num % 5 == 0) {
System.out.println("FIZZBUZZ");
}
else if (num % 3 == 0) {
System.out.println("FIZZ");
}
else if (num % 5 == 0) {
System.out.println("BUZZ");
}
else {
System.out.println("OM PHAT SWAHA");
}
}
}
