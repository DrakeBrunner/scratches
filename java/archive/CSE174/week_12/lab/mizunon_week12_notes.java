import java.util.*;

public class mizunon_week12_notes {
    public static void main(String[] args) {
        /* 
         * Send an array of integers to myFunction() and have it return the min() of
         * the first 2 elements with the max() of the last 2 elements
         */
        int[] send = {3, 4, 2, 1};
        System.out.println(myFunction(send));
    }
    
    public static int myFunction(int[] nums) {
        int min, max;
        if (nums.length >= 2) {
            min = min(nums[0], nums[1]);
            max = max(nums[nums.length - 2], nums[nums.length - 1]);
        }
        else
            return -1;

        return min * max;
    }

    public static int min(int a, int b) {
        if (a < b)
            return a;
        else
            return b;
        /*
         * Or, it can be written as
         * if (a < b)
         *   return a;
         * return b;
         *
         * or...
         *
         * return (a < b) ? a : b;
         */
    }

    public static int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }
}
