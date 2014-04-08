#include <stdio.h>

int main() {
    printf("Please input n and r (n >= r)\n");
    int n, r;
    scanf("%d%d", &n, &r);

    printf("%d\n", permutation(n, r));

    return 0;
}

int factorial(int n){
    if (n == 1)
        return 1;

    return n * factorial(n - 1);
}

int permutation(int n, int r) {
    if (r == 0)
        return 1;
    if (r == 1)
        return n;
    if (n == r)
        return factorial(n);

    return permutation(n - 1, r) + r * permutation(n - 1, r - 1);
}
