#include <stdio.h>

int main() {
    int n, i, num, set;
    int input[200] = {0};

    // Input number to start with
    printf("From what number? ");
    scanf("%d", &num);

    set = num;
    // Initialize array with num (from the bottom)
    for (i = 200 - 1; ; i--) {
        input[i] = set % 10;
        set /= 10;
        if (set / 10 == 0) {
            input [i - 1] += set;
            break;
        }
    }

    // Multiply from the top of the previous product
    // i represents the element,
    // n represents the number to be multiplied with.
    for (n = num - 1; n > 0; n--) {
        for (i = 0; i < 200; i++) {
            if ( (n * input[i]) >= 10) {
                input[i - 1] += (n * input[i]) / 10;
            }
            input[i] = (n * input[i]) % 10;
        }
    }

    // Print the number from the top
    int flag = 0;
    for (i = 0; i < 200; i++) {
        if (input[i] == 0 && flag == 0) {
            continue;
        }
        printf("%d", input[i]);
        flag = 1;
    }
    putchar('\n');

    return 0;
}
