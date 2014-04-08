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

    int length = 0;
    while (str[length])
        length++;

    int index_for_str = 0;
    int i;
    for (i = 0; i < length; i++) {
        if (!(str[i] >= '0' && str[i] <= '9'))
            str[index_for_str++] = str[i];
    }

    // Get rid of the rest after deleting all numbers
    // Original:
    //      a82lshliwhdk2lsi84028jlak
    // After deleting:
    //      alshliwhdklsijlak(<= finished processing)4028jlak(<= these remain)
    for (i = index_for_str; i < length; i++) {
        str[i] = '\0';
    }
}
