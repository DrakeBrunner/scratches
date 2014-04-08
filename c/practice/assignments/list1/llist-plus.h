#ifndef _llist_plus
#define _llist_plus

#include "llist.h"

/* Add Nth node to list */
void InsertNodeNth(Node **top, int n, int no, char *name);

/* Delete Nth node from list */
void DeleteNodeNth(Node **top, int n);

#endif
