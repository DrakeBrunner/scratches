#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const* argv[]) {
    char * c;
    char ch;
    int i = 0;
    while ((ch = getchar()) != '\n') {
        c = (char *)realloc(c, sizeof(char) * (i + 1));
        c[i] = ch;
        i++;
    }

    printf("%d\n", i);
    return 0;
}
