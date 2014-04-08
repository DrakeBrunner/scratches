#include <stdio.h>

// This is a run length encoding program.
// For example, aaaaabbbccdddda
// becomes a5b3c2d4a
// Version 2 avoids making the data larger than original
// by determining when to display length and when not to.

// Prototype
void runlength(int length, char *str_org, char *str_enc);

int main() {
    char buf_r[1024] = {0};
    char buf_w[1024] = {0};

    printf("Enter string: ");
    scanf("%s", buf_r);

    runlength(1024, buf_r, buf_w);

    printf("[Run Length Encoding]\n%s\n ", buf_w);

    return 0;
}

void runlength(int length, char *str_org, char *str_enc) {

    length = 0;
    while (str_org[length])
        length++;
    length++;

    int i;
    char previous_letter = '\0';
    char count = '0';
    int index_for_enc = 0;
    for (i = 0; i < length; i++) {
        if (str_org[i] == previous_letter)
            count++;
        else {
            // Ignore when i == 0
            if (i != 0 && index_for_enc < 1024) {
                str_enc[index_for_enc++] = previous_letter;
                // Don't write the count if the count is 1
                // because it is a waste of data
                if (count != '1')
                    str_enc[index_for_enc++] = count;
            }
            previous_letter = str_org[i];
            count = '1';
        }
    }
}
