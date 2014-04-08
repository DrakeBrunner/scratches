#include <stdio.h>

int main() {
    int a, b;

    printf("Input number 1: ");
    scanf("%d", &a);

    printf("Input number 2: ");
    scanf("%d", &b);


    printf("A is %f%% of B.\n", (double)100 * a / b);

    return 0;
}
