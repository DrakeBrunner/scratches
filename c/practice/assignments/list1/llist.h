/* llist.h */
#ifndef _llist
#define _llist

#define NAMELEN 10

/* Node */
typedef struct __node {
    int     no;             /* Member ID */
    char    name[NAMELEN];  /* Name */
    struct __node   *next;  /* Pointer for the next node */
} Node;

/* Set value to each member of Struct Node */
void SetNode(Node *x, int no, char *name, Node *next);

/* Allocate one node */
Node *AllocNode(void);

/* Insert Node to front */
void InsertNode(Node **top, int no, char *name);

/* Append Node to the last */
void AppendNode(Node **top, int no, char *name);

/* Delete first node */
void DeleteNode(Node **top);

/* Delete All nodes */
void ClearList(Node **top);

/* Display all nodes */
void PrintList(Node **top);

/* Initialize list */
void InitList(Node **top);

/* Terminate the use of list */
void TermList(Node **top);

#endif
