#include <stdio.h>

/**
 * This program uses arrays to calculate multiplications of large numbers
 */

// TODO: Change LENGTH to a variable and find the length of bigger number
#define LENGTH 16
#define CHUNK 2

// Prototypes
void reverse_array(int arr[]);
void multiply(int a[], int b[], int result[]);
void add(int arr[], int num);
void init(int arr[]);
void print_array(int arr[]);
int power(int num, int power);

int main() {
    int a[LENGTH];
    int b[LENGTH];

    char c;
    int i = 0;
    printf("Enter first num: ");
    while ((c = getchar()) != '\n')
        if (c >= 48 && c <= 57)
            a[i++] = c - 48;
    a[i] = -1;

    i = 0;
    printf("Enter second num: ");
    while ((c = getchar()) != '\n')
        if (c >= 48 && c <= 57)
            b[i++] = c - 48;
    b[i] = -1;

    //Checking if the input is correctly stored
    print_array(a);
    print_array(b);

    reverse_array(a);
    reverse_array(b);

    // After reversing
    /* print_array(a); */
    /* print_array(b); */

    int result[LENGTH * 2];
    multiply(a, b, result);

    print_array(result);

    return 0;
}

/**
 * Changes the order of array so that it starts with the first digit.
 * For example, an array with a length of 8 containing
 * "12345   " will become "54321000"
 * The array should mark the end of the number with a -1
 */
void reverse_array(int target[]) {

    // Scan from back and star for loop once it finds -1 in the original array
    int j = LENGTH - 1;
    while (target[j] != -1)
        j--;
    // Start from 1 left from the -1
    j--;
    // Remember where the number ends in the array
    int end = j + 1;

    int i = 0;
    while (i <= j) {
        int tmp = target[i];
        target[i] = target[j];
        target[j] = tmp;
        i++;
        j--;
    }

    // Make the rest 0
    for (i = end; i < LENGTH; i++)
        target[i] = 0;
}

/**
 * Multiplies the given 2 arrays. This function adds the result of the
 * multiplications of chunks to get the result.
 */
void multiply(int a[], int b[], int result[]) {
    init(result);

    int counter1 = 0;
    int counter2;
    int i, j;
    // Outer for loop
    for (i = 0; i < LENGTH / CHUNK; i++) {
        // First number to be multiplied
        // For example, if one number was 12345678 and the chunk was 4,
        // after being reversed, num1 would be 5678
        int num1 = 0;
        int loop;
        for (loop = 0; loop < CHUNK; loop++)
            num1 += power(10, loop) * a[counter1];

        // Inner for loop
        for (j = 0; j < LENGTH / CHUNK; j++) {
            int num2 = 0;
            for (counter2 = 0; counter2 < CHUNK; counter2++)
                num2 += power(10, counter2) * b[counter2];
            counter2 = 0;
            // DEBUG
            printf("num1: %d num2: %d\n", num1, num2);

            add(result, num1 * num2 * power(10, CHUNK * (i + j)));
        }
    }
}

/**
 * Adds the given number to the given array. When the number 123 is given and
 * the array contains "4282", which is 2824 because it is reversed, the
 * reverse of 2947, which is 7492 will be stored in the array.
 */
void add(int arr[], int num) {
    int i = 0;
    while (num != 0) {
        arr[i] += num % 10;
        num /= 10;
        i++;
    }
}

/**
 * Initializes the given array with 0.
 * The given array should have a length [LENGTH * 2]
 */
void init(int arr[]) {
    int i;
    for (i = 0; i < LENGTH * 2; i++)
        arr[i] = 0;
}

void print_array(int arr[]) {
    // Check if the array marks the end with -1 or if it's an array that uses
    // all the elements
    int i;
    for (i = 0; i < LENGTH; i++) {
        if (arr[i] == -1) {
            i = 0;
            while (arr[i] != -1)
                printf("%d", arr[i++]);
            printf("\n");
            return;
        }
    }
    // If it is, then print ALL of the elements
    for (i = 0; i < LENGTH; i++)
        printf("%d", arr[i]);
    printf("\n");
}

int power(int num, int power) {
    int i;
    int ret = 1;
    for (i = 0; i < power; i++)
        ret *= num;
    return ret;
}
