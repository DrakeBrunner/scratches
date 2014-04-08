#include <stdio.h>

int main(void)
{
    char str[] = "ABC";
    int i;
    printf("Before formatting: %s\n", str);

    for (i = 0; i < sizeof(str); i++) {
        str[i] = NULL;
    }
    printf("After formatting: %s\n", str);
    return 0;
}
