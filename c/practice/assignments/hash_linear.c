#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Definition of Hash table */
enum { TRUE = 1, FALSE = 0 };
enum { DELETED = -1, EMPTY = 0, FULL = 1 };

typedef struct {
    char key[1024];
    char value[1024];
    int state;  // FULL or EMPTY
} HashRecord;

typedef struct {
    int size;
    HashRecord *table;
} Hash;

/* Prototypes */
Hash* HashAlloc(int n);
void HashFree(Hash *);
int HashAdd(Hash *, char *, char *);
void HashDump(Hash *);
int HashDelete(Hash *, char *);
int HashGet(Hash *, char *, char *);
void HashClear(Hash *);
int HashCode(Hash *, char *);
/* ここに解答を書き加える */
/* Allocate hash and initialize */
Hash* HashAlloc(int n) {
    Hash *ret = malloc(sizeof(Hash));
    int i;

    ret->size = n;
    ret->table = calloc(n, sizeof(HashRecord));
    if (ret->table == NULL) {
        free(ret);
        printf("Failed to allocate hash. Returning NULL");
        return NULL;
    }

    HashClear(ret);

    return ret;
}

void HashFree(Hash *hash) {
    HashClear(hash);
    free(hash);
}

int HashAdd(Hash *hash, char *key, char *value) {
    int i = HashCode(hash, key);
    int j;

    for (j = 0; j < hash->size; j++) {
        if (hash->table[i].state == EMPTY) {
            strcpy(hash->table[i].key, key);
            strcpy(hash->table[i].value, value);
            hash->table[i].state = FULL;

            return TRUE;
        }

        if (strcmp(hash->table[i].key, key) == 0)
            return FALSE;

        i = (i + 1) % hash->size;
    }
    // In case you couldn't find a single EMPTY
    for (j = 0; j < hash->size; j++) {
        if (hash->table[i].state == DELETED) {
            strcpy(hash->table[i].key, key);
            strcpy(hash->table[i].value, value);
            hash->table[i].state = FULL;

            return TRUE;
        }

        i = (i + 1) % hash->size;
    }

    return FALSE;
}

int HashDelete(Hash *hash, char *key) {
    int i = HashCode(hash, key);
    int j;

    for (j = 0; j < hash->size; j++) {
        if (strcmp(hash->table[i].key, key) == 0) {
            hash->table[i].key[0] = '\0';
            hash->table[i].value[0] = '\0';
            hash->table[i].state = DELETED;

            return TRUE;
        }

        i = (i + 1) % hash->size;
    }

    return FALSE;
}

int HashGet(Hash *hash, char *key, char *value) {
    int i = HashCode(hash, key);
    int j;
    for (j = 0; j < hash->size; j++) {
        if (strcmp(hash->table[i].key, key) == 0) {

            strcpy(value, hash->table[i].value);

            return TRUE;
        }

        i = (i + 1) % hash->size;
    }
    return FALSE;
}

void HashClear(Hash *hash) {
    int size = hash->size;

    int i = 0;
    for (i = 0; i < size; i++) {
        hash->table[i].key[0] = '\0';
        hash->table[i].value[0] = '\0';
        hash->table[i].state = EMPTY;
    }
}

// ハッシュ値を求める
int HashCode(Hash *hash, char *key) {
    int n = strlen(key);
    if (n == 1)
        return key[0] % hash->size;

    return (key[0] - 'A' + (key[n / 2 - 1] - 'A') * 26 + (key[n - 2] - 'A') * 26 * 26) % hash->size;
}

/* Show Hash table */
void HashDump(Hash *hash) {
    int i;
    for (i = 0; i < hash->size; i++) {
        if (hash->table[i].state == FULL)
            printf("%d: %s = %s\n", i, hash->table[i].key, hash->table[i].value);
        else
            printf("%d: %s\n", i, hash->table[i].state == EMPTY ? "empty" : "deleted");
    }
}

int main(void) {
    int size = 0, h;
    char cmd, key[1024], value[1024], buf[1024];
    Hash *hash;

    // Create Hash table
    printf("Enter size of Hash table: ");
    scanf("%d", &size);
    if (size < 1)
        return -1;  // Invalid size
    hash = HashAlloc(size);
    if (hash == NULL)
        return -1;  // Hash allocation failed
    puts("* Enter one of the following commands");
    puts("* Input data:         a");
    puts("* Delete Key:         x");
    puts("* Check Key Status:   s");
    puts("* Get Value for key:  g");
    puts("* Show Hash Table:    d");
    puts("* Show Hash Value:    h");
    puts("* Clear Hash Table:   c");
    puts("* Quit:               q");

    do {
        printf(": ");
        scanf("%s", buf);
        cmd = buf[0];

        switch (cmd) {
            case 'a':
                printf("Enter name: ");
                scanf("%s", key);
                printf("Enter blood type: ");
                scanf("%s", value);
                if (HashAdd(hash, key, value) == TRUE)
                    printf("%s => %s\n", key, value);
                else
                    printf("That key already exists\n");
                break;
            case 'd':
                HashDump(hash);
                break;
            case 'c':
                HashClear(hash);
                break;
            case 'x':
                printf("Who do you want to delete? \n");
                scanf("%s", key);
                if (HashDelete(hash, key) == TRUE)
                    printf("Deleted %s\n", key);
                else
                    printf("%s is not registered\n", key);
                break;
            case 'g':
                printf("Enter name: ");
                scanf("%s", key);
                if (HashGet(hash, key, value) == TRUE)
                    printf("%s => %s\n", key, value);
                else
                    printf("%s is not registered\n", key);
                break;
            case 'h':
                printf("Enter name: ");
                scanf("%s", key);
                h = HashCode(hash, key);
                printf("Hash Value for %s is %d\n", key, h);
                break;
            case 'q':
                puts("Quitting");
                break;
            case '\n':
            case '\r':
                break;
            default:
                puts("Invalid Choice");
                break;
        }
    } while (cmd != 'q');

    HashDump(hash);
    HashFree(hash);

    return 0;
}
