#include <stdio.h>

char *str_chr(const char *str, int c);

int main(void) {
    char str[10], *p;
    char uletter[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    printf("Please enter uppercase alphabet: ");
    scanf("%s", str);

    p = str_chr(uletter, str[0]);
    if (p != NULL)
        puts(p);
    else
        puts("The letter entered is not an uppercase alphabet");

    return 0;
}

char *str_chr(const char *str, int c) {

    const char *marker = str;

    int i;
    for (i = 0; i < 26; i++) {
        if (*str++ == c) {
            return (char *)marker;
        }
        *marker++;
    }
    return NULL;
}
