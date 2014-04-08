#include <stdio.h>

int main() {
    int subsection, n, twice, max;
    int i, j;
    int i_start;
    printf("Max: ");
    scanf("%d", &max);

    i_start = max;
    for (subsection = 1; subsection < (max / 2 + 1); subsection++) {
        i = i_start;
        j = i;
        // Repeat 2 times
        for (twice = 0; twice < 2; twice++) {
            for (n = 0; n < subsection; n++) {
                printf("%d ", i * j);
                i++;
                j--;
            }
            // Initialize for the next round
            i = i_start;
            j = i - 1;
        }
        i_start--;
    }

    return 0;
}
