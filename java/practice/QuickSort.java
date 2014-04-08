public class QuickSort {
    public void sort(int[] a) {
        int left = 0;
        int right = a.length - 1;

        int stack = 0;
        int[] lstack = new int[32];
        int[] rstack = new int[32];

        // Push
        lstack[stack] = left;
        rstack[stack] = right;
        stack++;

        while (stack > 0) {
            // Pop
            stack--;
            left = lstack[stack];
            right = rstack[stack];

            if (right - left <= 4) {
                // Do a bubble sort
                for (int i = 0; i < right; i++) {
                    for (int j = right; j > i; j--) {
                        if (a[j - 1] > a[j]) {
                            // Swap j and j - 1
                            int tmp = a[j];
                            a[j] = a[j - 1];
                            a[j - 1] = tmp;
                        }
                    }
                }
            }
            else {
                if (right - left <= 1)
                    continue;

                int center = partition(a, left, right);

                if (center + 1 < right) {
                    lstack[stack] = center + 1;
                    rstack[stack] = right;
                    stack++;
                }
                if (left < center - 1) {
                    lstack[stack] = left;
                    rstack[stack] = center - 1;
                    stack++;
                }
            }
        }
    }

    // Returns the second largest value from 3 sample elements in the array
    public int pivot(int[] a, int left, int right) {
        int mid = (int)((left + right) / 2);

        if (a[left] > a[right])
            return a[mid] > a[left] ? left : a[mid] > a[right] ? mid : right;
        else
            return a[mid] > a[right] ? right : a[mid] > a[left] ? mid : left;
    }

    public int partition(int[] a, int left, int right) {
        int pivot = pivot(a, left, right);
        // Swap pivot and left
        int tmp = a[left];
        a[left] = a[pivot];
        a[pivot] = tmp;
        pivot = left;

        int i, j;
        i = left + 1;
        j = right;

        while (true) {
            while (a[i] < a[pivot])
                i++;
            while (a[j] > a[pivot])
                j--;

            if (i >= j)
                break;

            // Swap i and j
            tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;

            // Move i and j
            i++;
            j--;
        }

        // Swap pivot (= left) and j
        tmp = a[j];
        a[j] = a[pivot];
        a[pivot] = tmp;

        // Return the center
        return j;
    }
}