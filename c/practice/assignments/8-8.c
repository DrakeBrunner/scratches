#include <stdio.h>

int main(int argc, char const* argv[])
{
    int ch;
    int count = 0;

    while ((ch = getchar()) != EOF) {
        if (ch == '\n') {
            count++;
        }
    }
    printf("There were %d lines.\n", count);
    return 0;
}
