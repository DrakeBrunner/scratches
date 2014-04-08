import java.util.*;

public class array_out_of_bound {
    public static void main(String[] args) {
        int i = 0;
        int[] a = {1, 2, 3};

        while (a[i] < 10)
            i++;

        System.out.printf("i = %d", i);
    }
}