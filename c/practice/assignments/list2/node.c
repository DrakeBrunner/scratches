#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "node.h"

/* Set value to node */
void SetNode(Node *x, int no, char *name, Node *next) {
    x->no = no;
    x->next = next;
    strcpy(x->name, name);
}

/* Allocate Node */
Node *AllocNode(void) {
    return (calloc(1, sizeof(Node)));
}
