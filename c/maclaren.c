#include <stdio.h>
#define ACCURACY 0.000000000000000000000000000000001

double maclaren(int);
long long int factorial(int);

int main(void) {
    double e = maclaren(0);
    printf("Result; %.50f\n", e);

    return 0;
}

double maclaren(int n) {
    double x = 1 / (double)factorial(n);

    if (x < ACCURACY) {
        return 0;
    }
    else {
        return (x + maclaren(++n));
    }
}

long long int factorial(int n) {
    if (n == 0) {
        return (long long int)1;
    } else {
        return (long long int)(n * factorial(n - 1));
    }
}
