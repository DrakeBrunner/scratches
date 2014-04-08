#include <stdio.h>

char word[80];
char key[20];
char input;
int n;
int count_word;
int count_key;

void encrypt() {

    // Repeat adding each input value
    // to the char code of 'word'
    for (n = 0; n < count_word; n++) {
        word[n] += key[n % count_key];
    }
}

void decrypt() {

    // Repeat subtracting each input value
    // to the char code of 'word'
    for (n = 0; n < count_word; n++) {
        word[n] -= key[n % count_key];
    }

}

void show_word() {
    for (n = 0; n < count_word; n++) {
        putchar(word[n]);
    }
    putchar('\n');
}

void get_key() {
    count_key = 0;
    // Get key (=number of char codes to diff)
    printf("Input key to decrypt: ");
    for (n = 0; (input = getchar()) != '\n'; n++) {
        key[n] = input - '0';
        count_key++;
    }
}

int main(void) {

    printf("Input string you want to encrypt: ");

    for (n = 0; (input = getchar()) != '\n'; n++) {
        word[n] = input;
        count_word++;
    }

    get_key();
    encrypt();
    show_word();

    get_key();
    decrypt();
    show_word();

    return 0;

}
