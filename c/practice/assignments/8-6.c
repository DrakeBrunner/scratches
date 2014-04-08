#include <stdio.h>

int product_so_far = 1;

int combination(int n, int r) {
    if (r > 0) {
        product_so_far = n * combination(n - 1, r -1);
    }
    return product_so_far;
}

int main() {
    int n, r;
    puts("For nCr,");
    printf("    n = "); scanf("%d", &n);
    printf("    r = "); scanf("%d", &r);

    if (n < 0 || r < 0) {
        puts("Less than 0? You're kidding me, right?");
        return 0;
    }
    if (n < r) {
        puts("n shouldn't be smaller than r!");
        return 0;
    }
    if (r > n / 2) {
        r = n - r;
    }
    printf("%dC%d is %d.\n", n, r, combination(n, r) );
    return 0;
}
