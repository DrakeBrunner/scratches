#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const* argv[]) {
    int * a;
    int n;
    // Get length of array
    scanf("%d", &n);
    a = malloc(sizeof(int) * n);

    int i;
    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    free(a);
    return 0;
}
