#include <stdio.h>

int main(void)
{
    int input, i, j;
    int count[10] = {0};
    printf("Input number\n");
    
    do {
        input = getchar();
        count[input - '0']++;
    } while (input != EOF);

    for (i = 0; i < 10; i++) {
        printf("%d: ", i);

        for (j = 0; j < count[i]; j++) {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}
