// 課題2
#include <stdio.h>

double func(double x, double y) {
    return x * y;
}

int main() {
    double x = 3.2;
    double y = 2.1;
    double z = func(x, y);
    printf("%.2f\n", z);

    return 0;
}

// 課題3
#include <stdio.h>

int main() {
    int a[2][2] = { {1, 2}, {3, 4} };

    printf("a[0][0]=%d\n", a[0][0]);
    printf("a[0][1]=%d\n", a[0][1]);
    printf("a[1][0]=%d\n", a[1][0]);
    printf("a[1][1]=%d\n", a[1][1]);
    printf("|a|=%d\n", a[0][0] * a[1][0] - (a[0][1] * a[1][1]));
}

// 課題4
#include <stdio.h>

int main() {
    int a[2][3] = { {6, 5, 4}, {3, 2, 1} };
    int b[3][2] = { {1, 2}, {3, 4}, {5, 6} };
    int i, j;

    int c[2][2];

    for (j = 0; j < 2; j++) {
        for (i = 0; i < 3; i++) {
            c[0][j] += a[0][i] * b[i][j];
        }
    }

    for (j = 0; j < 2; j++) {
        for (i = 0; i < 3; i++) {
            c[1][j] += a[1][i] * b[i][j];
        }
    }
    printf("c[0][0]=%d\n", c[0][0]);
    printf("c[0][1]=%d\n", c[0][1]);
    printf("c[1][0]=%d\n", c[1][0]);
    printf("c[1][1]=%d\n", c[1][1]);

    return 0;
}
