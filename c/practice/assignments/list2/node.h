#ifndef _node
#define _node

#define NAMELEN 10

/* Node */
typedef struct __node {
    int no;                 /* Number */
    char name[NAMELEN];     /* Name */
    struct __node *next;    /* Pointer to next node */
} Node;

/* Allocate 1 node */
Node *AllocNode(void);

/* Set Node */
void SetNode(Node *x, int no, char *name, Node *next);

#endif
