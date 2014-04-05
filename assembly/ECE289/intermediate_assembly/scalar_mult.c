#include <stdio.h>
#include <stdlib.h>

int ** matrix_scalar_multiply(int ** m, int col, int row, int scalar) {
    int ** r_m;
    int i, j;

    r_m = (int **)malloc(sizeof(int *) * col);
    for (i = 0; i < col; i++)
        r_m[i] = (int *)malloc(sizeof(int) * row);

    // Same as...
    while (i < col) {
        while (j < row) {
            // Do something
            j++;
        }
        i++;
    }
    //

    for (i = 0; i < col; i++) {
        for (j = 0; j < row; j++) {
            r_m[i][j] = m[i][j] * scalar;
        }
    }

    return r_m;
}

int main(int argc, char const* argv[]) {
    int ** m;
    int col = 5;
    int row = 3;

    m = (int **)malloc(sizeof(int *) * col);

    int i, j;
    for (i = 0; i < col; i++)
            *(m + i) = (int *)malloc(sizeof(int) * row);

    for (i = 0; i < col; i++) {
        for (j = 0; j < row; j++) {
            scanf("%d", &m[i][j]);
        }
    }

    matrix_scalar_multiply(m, col, row, 4);

    return 0;
}
