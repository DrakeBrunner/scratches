#include <stdio.h>
#define N 10

void quicksort(int a[]);
int partition(int a[], int l, int r);
int pivot(int a[], int l, int r);
void swap(int *a, int *b);

int main(void) {
    int a[] = {863, 52, 628, 888, 32, 303, 607, 388, 944, 91};

    int i;
    printf("Before:\n");
    for (i = 0; i < N; i++)
        printf("%d ", a[i]);
    puts("\n");

    quicksort(a);

    printf("After:\n");
    for (i = 0; i < N; i++)
        printf("%d ", a[i]);
    puts("\n");

    return 0;
}

/**
 * A function that performs a quicksort for the given array of int.
 */
void quicksort(int a[]) {
    // Stack for the left and right endpoints
    int l[32];
    int r[32];

    // Add the first endpoints (first and last element)
    l[0] = 0;
    r[0] = N - 1;

    // Counter of the number of elements left in the stack
    int s = 1;

    while (s > 0) {
        // The next pivot (= center value)
        s--;
        int left = l[s];
        int right = r[s];

        if (right - left <= 4) {
            int i, j;
            for (i = N - 1; i > 0; i--)
                for (j = i; j < N; j++)
                    if (a[j - 1] > a[j])
                        swap(&a[j - 1], &a[j]);
            continue;
        }

// CHECK!
        if (right - left <= 1)
            continue;

        int center = partition(a, left, right);

// CHECK!
        if (center - 1 > left) {
            l[s] = left;
            r[s] = center - 1;
            s++;
        }
// CHECK!
        if (center + 1 < right) {
            l[s] = center + 1;
            r[s] = right;
            s++;
        }
    }
}

/**
 * Changes the order of the array according to the pivot's value.
 * Returns the index of the next pivot.
 */
int partition(int a[], int l, int r) {
    // Get the pivot
    int p = pivot(a, l, r);

    // First, swap the pivot and the first element in the array
    swap(&a[l], &a[p]);
    p = l;

    int i = l + 1;
    int j = r;

    while (1) {
        while (a[i] < a[p])
            i++;
        while (a[j] > a[p])
            j--;

        if (i >= j)
            break;

        swap(&a[i], &a[j]);
        i++;
        j--;
    }

    swap(&a[j], &a[p]);

    return j;
}

/**
 * Returns the pivot value that will be used
 * as a threshold value when sorting.
 * l is the left endpoint and r is the right endpoint.
 * The index of the second largest number when comparing
 * the left, middle, and right number of the array is returned.
 */
int pivot(int a[], int l, int r) {
    int mid = (l + r) / 2;

    if (a[l] > a[r])
        return a[mid] > a[l] ? r :
            a[mid] > a[r] ? mid : l;
    else
        return a[mid] > a[r] ? l :
            a[mid] > a[l] ? mid : r;
}

/**
 * Swaps the given integers by using its pointer.
 */
void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}
