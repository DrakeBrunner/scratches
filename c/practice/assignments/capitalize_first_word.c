#include <stdio.h>

// Assignment
// This program will change the lowercase from input to uppercase.

void replace_digit(char str[]);

int main() {
    char str[100];
    printf("Enter string: ");
    scanf("%s", str);

    printf("Change it to capital\n");

    replace_digit(str);

    printf("str = %s", str);

    return 0;
}

void replace_digit(char str[]) {
    char *ptr = str;

    // Start

    // Get the difference between capital A and lowercase a
    int diff = 'a' - 'A';
    // Get the length of the array
    int length = 0;
    while (ptr[length])
        length++;

    // Subtract the difference if it's not a-z
    int i;
    for (i = 0; i < length; i++) {
        if (ptr[i] >= 'a' && ptr[i] <= 'z')
            ptr[i] -= diff;
    }

    return;
    // End

    *ptr = '\0';
}
