/* 二分木の課題1 */

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
    if (p == NULL) {
        p = AllocNode();
        strcpy(p->name, w->name);
        p->right = p->left = NULL;
    }
    // If the name of "w" corresponds with the name of "p"
    else if ((cond = strcmp(w->name, p->name)) == 0) {
        printf(" %s has already been registered\n", w->name);
        // ここに解答を書き加える
        (p->count)++;
    }
    else if (cond < 0)
        p->left = ApndNode(p->left, w);
    else
        p->right = ApndNode(p->right, w);
    return p;
}

void SrchNode(BinNode *p, BinNode *w) {
    int cond;
    if (p == NULL)
        printf("%s is not registered\n", w->name);
    else if ((cond = strcmp(w->name, p->name)) == 0)
        printf("%s is registered\n", w->name);
    else if (cond < 0)
        SrchNode(p->left, w);
    else
        SrchNode(p->right, w);
}

void PrintTree(BinNode *p) {
    if (p != NULL) {
        PrintTree(p->right);
        printf("%s(%d)\n", p->name, p->count);
        PrintTree(p->left);
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
    printf("Please Input name for %s: ", message);
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
