#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include "encrypt.h"
#include "rsa_functions.h"

RSAint chars2RSAint(char * str, int c) {
    // Check preconditions
    assert(str != NULL && 0 < c && c < (int)sizeof(RSAint));

    RSAint rsaInt = 0;
    int i;
    for (i = 0; i < c; i++)
        // Shift 8 bits to the right
        rsaInt += (RSAint)(*(str + i) << (i * 8));

    return rsaInt;
}

void RSAint2chars(RSAint enc, char * str, int c) {
    // Check preconditions
    assert(enc >= 0 && c >= 0 && str != NULL);

    int i;
    for (i = 0; i < c; i++) {
        *(str + i) = (char)enc % (1 << 8);
        enc = enc >> 8;
    }
}

void generateKey(RSAint p, RSAint q, RSAint * e, RSAint * d, RSAint * n) {
    // Check preconditions
    if (n == NULL)
        std::cout << "null" << std::endl;
    assert(e != NULL && d != NULL && n != NULL
            && minPrimeLimit < p && p < maxPrimeLimit
            && minPrimeLimit < q && q < maxPrimeLimit
            && p != q);

    *n = p * q;
    RSAint totient = (p - 1) * (q - 1);

    seedRand();
    do {
        *e = randInt(1, totient);
        if (!(*e < totient && isCoPrime(*e, totient)))
            continue;
        *d = multiplicative_inverse(*e, totient);
    } while (!(*e > 10000 && *e * *d % ((p - 1) * (q - 1)) == 1));

    // Postcondition
    assert(*n == p * q && *e * *d % ((p - 1) * (q - 1)) == 1 && *e > 10000);
}

RSAint * RSAencrypt(char * msg, int size, int * newSize, RSAint e, RSAint n) {
    assert(msg != NULL && newSize != NULL && size > 0);

    *newSize = (size - 1) / 3 + 1;

    RSAint * rsaIntArray = (RSAint *)malloc(sizeof(RSAint) * *newSize);
    int i;
    int arrayCount = 0;
    for (i = 0; i < size; i += 3) {
        char c[] = { msg[i], msg[i + 1], msg[i + 2] };
        RSAint rawRSAint = chars2RSAint(c, 3);
        RSAint encryptedRSAint = modular_power(rawRSAint, e, n);

        rsaIntArray[arrayCount] = encryptedRSAint;
        arrayCount++;
    }

    // Remaining 1 or 2 characters
    if (size % 3 == 1) {
        char c[] = { msg[i] };
        RSAint rawRSAint = chars2RSAint(c, 1);
        RSAint encryptedRSAint = modular_power(rawRSAint, e, n);
        // Push to array
        rsaIntArray[arrayCount] = encryptedRSAint;
    }
    else if (size % 3 == 2) {
        char c[] = { msg[i], msg[i + 1] };
        RSAint rawRSAint = chars2RSAint(c, 2);
        RSAint encryptedRSAint = modular_power(rawRSAint, e, n);
        // Push to array
        rsaIntArray[arrayCount] = encryptedRSAint;
    }

    return rsaIntArray;
}

char * RSAdecrypt(RSAint * encoding, int size, int * newSize, RSAint d, RSAint n) {

    char * chars = (char *)malloc(sizeof(char) * size * 3);

    int arrayCount = 0;
    int i;
    char * c = (char *)malloc(sizeof(char) * 3);
    for (i = 0; i < size; i++) {
        RSAint decryptedChunk = modular_power(*(encoding + i), d, n);

        RSAint2chars(decryptedChunk, c, 3);

        // Copy retrieved string to returning string
        int j;
        for (j = 0; j < 3; j++) {
            if (*(c + j) == 0)
                break;
            *(chars + arrayCount) = *(c + j);
            arrayCount++;
        }

    }

    *newSize = arrayCount;

    // TODO: Why SIGSEGV when including the following line?
    // free(c);
    return chars;
}

RSAint RSACracker(RSAint e, RSAint n) {
    RSAint d;
    RSAint p = 2;
    while (true) {
        RSAint q = n / p;

        if (n == p * q && isCoPrime(p, q)) {
        // if (n == p * q && isPrime(p) && isPrime(q)) {
            d = multiplicative_inverse(e, (p - 1) * (q - 1));
            break;
        }
        p++;
    }
    printf("Cracker Generated:\n  p: %lld\n  q: %lld\n  d: %lld\n", p, n / p, d);

    return d;
}
