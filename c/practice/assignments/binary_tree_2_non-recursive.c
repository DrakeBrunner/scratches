/* 二分木の課題3 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LEN 128
typedef enum {
    Term, Insert, Search, Print
} Menu;

typedef struct _bnode{
    char name[MAX_LEN];
    struct _bnode *left;
    struct _bnode *right;
    // Counter
    int count;
} BinNode;

BinNode *AllocNode(void) {
    // ここに解答を書き加える
    BinNode *ret;
    ret = calloc(1, sizeof(BinNode));
    if (ret == NULL) {
        printf("Failed to Allocate Node. Returning NULL\n");
        return NULL;
    }
    else {
        ret->count = 1; // It's the first time that name appears
        return ret;
    }
}

BinNode *ApndNode(BinNode *p, BinNode *w) {
    int cond;
    BinNode *c = p;
    while (1) {
        if (p == NULL) {
            p = AllocNode();
            strcpy(p->name, w->name);
            p->right = p->left = NULL;
            break;
        }
        // If the name of "w" corresponds with the name of "p"
        else if ((cond = strcmp(w->name, c->name)) == 0) {
            printf(" %s has already been registered\n", w->name);
            // ここに解答を書き加える
            (c->count)++;
            return p;
        }
        else if (cond < 0) {
            if (c->left == NULL) {
                c->left = AllocNode();
                c = c->left;
                strcpy(c->name, w->name);
                c->right = c->left = NULL;
                break;
            }
            c = c->left;
        }
        else {
            if (c->right == NULL) {
                c->right = AllocNode();
                c = c->right;
                strcpy(c->name, w->name);
                c->right = c->left = NULL;
                break;
            }
            c = c->right;
        }
    }
    return p;
}

void SrchNode(BinNode *p, BinNode *w) {
    int cond;
    while (1) {
        if (p == NULL) {
            printf("%s is not registered\n", w->name);
            break;
        }
        else if ((cond = strcmp(w->name, p->name)) == 0) {
            printf("%s is registered\n", w->name);
            break;
        }
        else if (cond < 0)
            p = p->left;
        else
            p = p->right;
    }
}

void PrintTree(BinNode *p) {
    if (p != NULL) {
        BinNode *c = p;
        BinNode **array;
        int i = 0;
        int max = i;
        while (1) {
            if (c != NULL) {
                if (*(array + i) == NULL)
                    *(array +i) = (BinNode *)malloc(sizeof(BinNode));
                *(array + i) = c;
                i++;
                if (max < i)
                    max = i;
                c = c->right;
            }
            else if (i > 0) {
                if (*(array + i) != NULL && i > max)
                    free(*(array + i));
                i--;
                c = *(array + i);
                printf("%s(%d)\n", c->name, c->count);
                c = c->left;
            }
            else
                break;
        }
    }
}

void FreeTree(BinNode *p) {
    if (p != NULL) {
        free(p->left);
        free(p->right);
        free(p);
    }
}

BinNode Read(char *message) {
    BinNode temp;
    printf("Please Input name to %s: ", message);
    scanf("%s", temp.name);
    return temp;
}

Menu SelectMenu(void) {
    int ch;
    do {
        printf("(1) Insert (2) Search (3) Print (0) Quit: ");
        scanf("%d", &ch);
    } while (ch < Term || ch > Print);

    return (Menu)ch;
}

int main(void) {
    Menu menu;
    BinNode *root;

    root = NULL;

    do {
        BinNode x;
        switch (menu = SelectMenu()) {
            case Insert:
                x = Read("Insert");
                root = ApndNode(root, &x);
                break;
            case Search:
                x = Read("Search");
                SrchNode(root, &x);
                break;
            case Print:
                puts("---------------");
                PrintTree(root);
                break;
        }
    } while (menu != Term);

    FreeTree(root);

    return 0;
}
