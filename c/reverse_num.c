#include <stdio.h>


int reverse(int input) {
    int times = 1;
    int out = 0;
    int tmp = input;

    while (tmp > 0) {
        tmp /= 10;
        times *= 10;
    }

    do {
        times /= 10;
        out += (input % 10) * times;
        input /= 10;
    } while (input > 0);


    return out;
}

int main(void) {
    int input;
    scanf("%d", &input);

    printf("out %d\n", reverse(input));

    return 0;
}
