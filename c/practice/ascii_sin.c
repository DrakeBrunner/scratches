#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define STR_SIZE 20

/*
 * This program prompts for a string and calculates the sum of the ASCII code
 * and prints out the sin of that sum (sum is the radian)
 */

char * get_char(int * length) {
    char * buf = (char *)malloc(sizeof(char) * STR_SIZE);
    size_t size = STR_SIZE;
    printf("Enter string: ");
    *length = getline(&buf, &size, stdin);

    return buf;
}

int main() {
    // Get string
    int str_len;
    char * buf = get_char(&str_len);

    // Calculate the sum of the ASCII code
    int ascii_sum = 0;
    int i;
    for (i = 0; i < str_len; i++)
        ascii_sum += *(buf + i);

    printf("Sum of ASCII code is %d\n", ascii_sum);
    printf("sin(%d) = %lf\n", ascii_sum, sin(ascii_sum));

    return 0;
}
