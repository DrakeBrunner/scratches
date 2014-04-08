#include <stdio.h>

/**
 * This program is for testing the behavior of bitwise processing.
 */

int count() {
    int cnt = 0;

    // u is 11111...1111
    unsigned u = ~0U;
    while (u) {
        cnt++;
        u >>= 1;
    }
    return cnt;
}

/**
 * Simply prints the bits. This uses the count() function to know when
 * the loop should end.
 */
void printBits(num) {
    int i;
    for (i = count() - 1; i >= 0; i--)
        putchar((num >> i & 1U) ? '1' : '0');
    putchar('\n');
}

/**
 * Given an unsigned number, it shifts the bits right by 1 bit,
 * and move that shifted bit to the front. For example,
 * when 00101 is shifted, it becomes 10010.
 */
void rRotate(num) {
    unsigned save = num;
    unsigned first = 1U;
    int i;
    // Making a 1000....000
    for (i = 0; i < count() - 1; i++)
        first <<= 1;
    num >>= 1;
    num = num | first;
    // When the first bit of num is 0, change the beginning to 0 by using XOR
    if (!(save & 1U))
        num = num ^ first;

    // And print
    printBits(num);
}

/**
 * Given an unsigned number, this function shifts the bits to the left
 * by 1 bit, and move the bit at the very beginning to the right.
 * For example, 10110 will become 01101.
 */
void lRotate(unsigned num) {
    unsigned checkBeginning = num;
    int i;
    for (i = 0; i < count() - 1; i++)
        checkBeginning >>= 1;

    num <<= 1;
    // Change the first bit to 1 if the last bit of the original number is 1
    if (checkBeginning)
        num |= 1U;
    // No need for else because 0 will automatically
    // be added when shifting right

    // And then print
    printBits(num);
}

int main() {

    unsigned num;
    printf("Enter unsigned num: ");
    scanf("%u", &num);

    // Count how many bits that data type has
    printf("That num is %d bits\n", count());

    // Show the bits
    printBits(num);

    // "Rotate" to the right (and print)
    printf("Right rotate\n");
    rRotate(num);

    // "Rotate" to the left (and print)
    printf("Left rotate\n");
    lRotate(num);

    return 0;
}
