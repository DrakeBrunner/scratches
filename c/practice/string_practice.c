#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Starts looking at the string from the back and removes the first '\n' that it encounters
 */
void chomp(char * buf, int size) {
    int i;
    for (i = size - 1; i >= 0; i--) {
        if (*(buf + i) == '\n') {
            *(buf + i) = '\0';
            return;
        }
    }
}

int main() {
    // Either this (getline() allocates memory)
    /* char * buf = NULL; */
    /* size_t size; */
    // Or this
    char * buf = (char *)malloc(sizeof(char) * 30);
    size_t size = 30;

    int n = getline(&buf, &size, stdin);
    printf("%u\n", strlen(buf));
    printf("size: %u\n", size);

    chomp(buf, n);
    printf("DEBUG: %s\n", buf);

    int i = 0;
    int counter = 0;
    char c;
    while ((c = *(buf + i++)) != '\0') {
        if (c == ' ')
            counter++;
    }

    printf("Number of spaces: %d\n", counter);
    return 0;
}
