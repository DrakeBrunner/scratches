#include <stdio.h>

int main(void) {
    int level, i, j;

    printf("How many levels? ");
    scanf("%d", &level);

    for (i = 1; i <= level; i++) {
        for (j = 0; j < level - i; j++) {
            printf(" ");
        }
        for (j = 0; j < i * 2 - 1; j++) {
            printf("*");
        }
        printf("\n");
    } 

    return 0;
}
