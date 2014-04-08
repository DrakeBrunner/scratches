/*
 *  Write the main function such that it:
 * Reads the public keys n and e from the file keys.txt. (n is listed first.)
 * Reads the contents of the file encrypted.txt, containing a sequence of RSAints
 * (separated by spaces) produced by applying the encryption algorithm to a file,
 * using the public key (e,n) you just read in.
 * Cracks the encryption and prints out the original message.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "rsa_functions.h"
#include "encrypt.h"

void testConvert() {
    int c = 3;
    char * str = "ABC";

    RSAint rsa = chars2RSAint(str, c);

    // String converted to long long int
    printf("%lld\n", rsa);

    // Convert back to string
    char * str2 = (char *)malloc(sizeof(char) * c);
    RSAint2chars(rsa, str2, c);
    int i;
    for (i = 0; i < c; i++) {
        printf("%c ", *(str2 + i));
    }
    printf("\n");
}

/*
 * Tests generating, encrypting, decrypting, and cracking
 */
void test() {
    // Choose prime numbers
    RSAint p, q;
    p = randomPrime(minPrimeLimit + 1, maxPrimeLimit);
    q = randomPrime(minPrimeLimit + 1, maxPrimeLimit);

    RSAint e, d, n;

    // Generate
    generateKey(p, q, &e, &d, &n);
    printf("Generated keys are\n  p: %lld\n  q: %lld\n  e: %lld\n  d: %lld\n  n: %lld\n", p, q, e, d, n);

    // Encrypt
    int * newSize = (int *)malloc(sizeof(int));
    RSAint * enc = RSAencrypt("HELLO WORLD!! :)", 16, newSize, e, n);
    printf("Encrypted:\n");
    int i;
    for (i = 0; i < *newSize; i++)
        printf("%lld ", *(enc + i));
    printf("\n");

    // Decrypt
    char * dec = RSAdecrypt(enc, *newSize, newSize, d, n);
    printf("Decrypted:\n");
    for (i = 0; i < *newSize; i++)
        printf("%c ", *(dec + i));
    printf("\n");

    free(enc);

    // Test crack
    printf("Test cracking d (should be %lld): %lld\n", d, RSACracker(e, n));
}

void testCrack() {
    int i;
    int * newSize = (int *)malloc(sizeof(int));

    // Crack
    FILE * file;
    file = fopen("keys.txt", "r");
    RSAint crack_n;
    fscanf(file, "%lld", &crack_n);
    RSAint crack_e;
    fscanf(file, "%lld", &crack_e);
    fclose(file);

    RSAint cracked_d = RSACracker(crack_e, crack_n);
    printf("Cracked: %lld\n", cracked_d);

    // Test if cracked correctly
    // Read in numbers
    int counter = 0;
    RSAint code;
    RSAint * array = (RSAint *)malloc(sizeof(RSAint) * 3000);

    // Read from file
    FILE * encryptedFile;
    encryptedFile = fopen("encrypted.txt", "r");

    while (fscanf(encryptedFile, "%lld ", &code) != EOF) {
        *(array + counter) = code;
        counter++;
    }
    fclose(encryptedFile);

    char * crack_dec = RSAdecrypt(array, counter, newSize, cracked_d, crack_n);
    for (i = 0; i < *newSize; i++)
        printf("%c", *(crack_dec + i));
    printf("\n");

    free(crack_dec);
    free(newSize);
    free(array);
}

void testMyCode() {
    int i;
    int * newSize = (int *)malloc(sizeof(int));

    // Crack
    FILE * file;
    file = fopen("debugKeys.txt", "r");
    RSAint crack_e;
    fscanf(file, "%lld", &crack_e);
    RSAint crack_n;
    fscanf(file, "%lld", &crack_n);
    fclose(file);

    RSAint cracked_d = RSACracker(crack_e, crack_n);
    printf("Cracked: %lld\n", cracked_d);

    // Test if cracked correctly
    // Read in numbers
    int counter = 0;
    RSAint code;
    RSAint * array = (RSAint *)malloc(sizeof(RSAint) * 3000);

    // Read from file
    FILE * encryptedFile;
    encryptedFile = fopen("myEncrypted.txt", "r");

    while (fscanf(encryptedFile, "%lld ", &code) != EOF) {
        *(array + counter) = code;
        counter++;
    }
    fclose(encryptedFile);

    char * crack_dec = RSAdecrypt(array, counter, newSize, cracked_d, crack_n);
    for (i = 0; i < *newSize; i++)
        printf("%c", *(crack_dec + i));
    printf("\n");

    free(crack_dec);
    free(newSize);
    free(array);
}

int main() {
    // test();
    testCrack();
    // testMyCode();

    return 0;
}
