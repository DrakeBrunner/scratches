#include <stdio.h>
#include <stdlib.h>
void foobar() {
    printf("Foobar\n");
}

int main(int argc, char const* argv[]) {
    void (*a)() = (void*)malloc(sizeof(void));
    a = foobar;
    a();
    printf("sizeof(void) = %lu\n", sizeof(void));
    printf("sizeof(a) = %lu\n", sizeof(a));
    return 0;
}
