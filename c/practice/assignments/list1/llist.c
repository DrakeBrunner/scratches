/* llist.c */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "llist.h"

/* Set Node */
void SetNode(Node *x, int no, char *name, Node *next) {
    x->no = no;
    x->next = next;
    strcpy(x->name, name);
}

/* Allocate Node */
Node *AllocNode(void) {
    return (calloc(1, sizeof(Node)));
}

/* Insert Node to the first */
void InsertNode(Node **top, int no, char *name) {
    Node *ptr = *top;
    *top = AllocNode();
    SetNode(*top, no, name, ptr);
}

/* Append Node to last */
void AppendNode(Node **top, int no, char *name) {
    if (*top == NULL)
        InsertNode(top, no, name);
    else {
        Node *ptr = *top;
        while (ptr->next != NULL)
            ptr = ptr->next;
        ptr->next = AllocNode();
        SetNode(ptr->next, no, name, NULL);
    }
}

/* Delete first node */
void DeleteNode(Node **top) {
    if (*top != NULL) {
        Node *ptr = (*top)->next;
        free(*top);
        *top = ptr;
    }
}

/* Delete all nodes */
void ClearList(Node **top) {
    while (*top != NULL)
        DeleteNode(top);    /* Delete first node */
}

/* Display all nodes */
void PrintList(Node **top) {
    Node *ptr = *top;
    puts("## List ##");
    while (ptr != NULL) {
        printf("%5d %-10.10s\n", ptr->no, ptr->name);
        ptr = ptr->next;
    }
}

/* Initialize List */
void InitList(Node **top) {
    *top = NULL;
}

/* Terminate the use of list */
void TermList(Node **top) {
    ClearList(top); /* Delete all nodes */
}
