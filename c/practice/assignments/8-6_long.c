#include <stdio.h>

unsigned long int product_so_far = 1;

unsigned long int combination(unsigned long int n, unsigned long int r) {
    if (r > 0) {
        product_so_far = n * combination(n - 1, r -1);
    }
    return product_so_far;
}

int main() {
    unsigned long int n, r;
    puts("For nCr,");
    printf("    n = "); scanf("%d", &n);
    printf("    r = "); scanf("%d", &r);

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
