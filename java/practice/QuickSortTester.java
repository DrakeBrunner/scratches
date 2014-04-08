import java.util.*;

public class QuickSortTester {
    public static final int N = 10;
    public static void main(String[] args) {

        QuickSort qs = new QuickSort();

        /* int[] array = new int[N]; */
        int[] array = {863, 52, 628, 888, 32, 303, 607, 388, 944, 91};

        /* for (int i = 0; i < array.length; i++) */
        /*     array[i] = (int)(Math.random() * 1000); */

        // Before sorting
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();

        qs.sort(array);

        // After sorting
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}