#include <stdio.h>

// 課題7
// This is a run length encoding program.
// For example, aaaaabbbccdddda
// becomes a5b3c2d4a1

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
                str_enc[index_for_enc] = previous_letter;
                str_enc[index_for_enc + 1] = count;
                index_for_enc += 2;
            }
            previous_letter = str_org[i];
            count = '1';
        }
    }
}
