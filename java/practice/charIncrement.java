public class charIncrement {
    public static void main(String[] args) {
        char c = 'A';

        for (int i = 0; i < 26; i++)
            System.out.printf("%c", c++);
    }
}