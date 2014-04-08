#include <stdio.h>

int main() {
    int a, b;

    printf("Input number 1: ");
    scanf("%d", &a);

    printf("Input number 2: ");
    scanf("%d", &b);


    printf("A is %d%% of B.\n", 100 * a / b);

    return 0;
}
