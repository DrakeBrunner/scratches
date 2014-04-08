#include <stdio.h>

void del_digit(char *str);

int main() {
    char str[100];

    printf("Enter string: ");
    scanf("%s", str);

    printf("%s\n", str);
    del_digit(str);

    puts("Numbers have been deleted");
    printf("%s\n", str);
    return 0;
}

void del_digit(char *str) {
    char *copy = str;

    while (1) {
        if (!(*copy >= '0' && *copy <= '9')) {
            *str = *copy;
            *str++;
        }
        if (!*(copy + 1)) {
            while (*str) {
                *str = '\0';
                *str++;
            }
            break;
        }
        *copy++;
    }
}
