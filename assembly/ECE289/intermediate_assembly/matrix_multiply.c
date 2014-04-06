#include <stdio.h>
#include <stdlib.h>

/**
 * mcol: number of columns in matrix m
 * mrow: number of rows in matrix m
 * ncol: number of columns in matrix n
 * nrow: number of rows in matrix n
 */
int ** matrix_multiply(int ** m, int mcol, int mrow, int ** n, int ncol, int nrow) {
	int ** r_m = NULL;
	int i, j, k;

	if (nrow != mcol)
		return r_m;

	r_m = (int **)malloc(sizeof(int *) * mrow);
	for (i = 0; i < mrow; i++)
		r_m[i] = (int *)malloc(sizeof(int) * ncol);

	for (i = 0; i < mrow; i++) {
		for (j = 0; j < ncol; j++) {
			r_m[i][j] = 0;
			for (k = 0; k < nrow; k++) {
				r_m[i][j] += m[i][k] * n[k][j];
			}
		}
	}

	return r_m;
}

/**
 * col: the number of columns
 * row: the number of rows
 */
int ** array(int col, int row) {
    int i, j;
    int ** a = (int **)malloc(sizeof(int *) * row);
    for (i = 0; i < row; i++)
        a[i] = (int *)malloc(sizeof(int) * col);

    for (i = 0; i < row; i++)
        for (j = 0; j < col; j++)
            scanf("%d", &a[i][j]);

    return a;
}

int main(int argc, char const* argv[]) {
    /* Number of rows */
    int mrow = 3;
    /* Number of columns */
    int mcol = 4;
    /* Number of rows */
    int nrow = 4;
    /* Number of columns */
    int ncol = 2;

    printf("m\n");
    int ** m = array(mcol, mrow);
    printf("n\n");
    int ** n = array(ncol, nrow);
    int ** result = matrix_multiply(m, mcol, mrow, n, ncol, nrow);

    int i, j;
    for (i = 0; i < mrow; i++) {
        for (j = 0; j < ncol; j++) {
            printf("%3d ", result[i][j]);
        }
        printf("\n");
    }

    return 0;
}
