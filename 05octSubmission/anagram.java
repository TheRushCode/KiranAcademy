// 5. WAP to find the word1 and word2 is Anagram or Not

public class Main {
    public static void main(String[] args) {

        String word1 = "listen";
        String word2 = "silent";

        if (word1.length() == word2.length())
            System.out.println("Anagram");
        else
            System.out.println("Not Anagram");
    }
}
