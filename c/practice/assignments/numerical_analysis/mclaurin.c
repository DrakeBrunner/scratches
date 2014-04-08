#include <stdio.h>
#include <math.h>

int factorial(int n) {
    if (n == 0)
        return 1;

    int ret = n;

    int i;
    for (i = n - 1; i > 0; i--)
        ret *= i;

    return ret;
}

int power(int num, int n) {
    int ret = 1;
    int i;
    for (i = 0; i < n; i++)
        ret *= num;

    return ret;
}

int main() {
    int terms;
    printf("Enter number of terms: ");
    scanf("%d", &terms);

    int x = 1;

    float result = 0.0;

    int n;
    for (n = 0; n < terms; n++)
        result += power(x, n) / (double)factorial(n);

    printf("Result         : %.32f\n", result);
    printf("Appected Result: %.32f\n", exp(1));

    float inaccuracy = (exp(1) - result) / exp(1) * 100;
    printf("Inaccuracy: %.32f%%\n", inaccuracy);

    return 0;
}
