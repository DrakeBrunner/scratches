#include <iostream>
#include <stdio.h>

#define MAX_COLUMN 30

/*
 * Quick-and-dirty program that tests the modular exponents (or whatever it's called)
 */

void println() {
    int i;
    std::cout << "---";
    for (i = 0; i < MAX_COLUMN * 5; i++) {
        if (i % 5 == 4)
            std::cout << "+";
        else
            std::cout << "-";
    }
    std::cout << std::endl;
}

int main() {
    std::cout << "Enter modulo: ";
    int modulo;
    std::cin >> modulo;

    int i, j;

    std::cout << "The rows show the number, columns show the exponent." << std::endl;
    std::cout << "   ";
    for (i = 0; i < MAX_COLUMN; i++)
        printf(" %2d  ", i + 1);
    std::cout << std::endl;

    println();

    for (j = 1; j < modulo; j++) {
        const int num = j;

        printf("%2d ", num);

        int product = num;
        for (i = 0; i < MAX_COLUMN; i++) {
            printf(" %2d |", product);
            product = (product * num) % modulo;
        }
        std::cout << std::endl;

        println();
    }
    return 0;
}
