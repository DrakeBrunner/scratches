#include <stdio.h>

int num, i;

int fact(int n) {
    for (i = n - 1; i > 0; i--) {
        n *= i;
    }

    return n;
}

int main(void) {
    do {
        printf("Of what number would you like to know the factorial? ");
        scanf("%d", &num);
        // Number has to be more than 0.
        // ...or was 0 okay?
        if (num > 0) {
            break;
        }
        puts("The number must be positive!\n");
    } while (1);

    printf("The answer is %d!!\n", fact(num) );

    return 0;
}
