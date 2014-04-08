#include <stdio.h>

int main() {
    int i = 0;
    int a[] = {1, 2, 3};

    while (a[i] < 10)
        i++;

    printf("i = %d\n", i);

    return 0;
}
