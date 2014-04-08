#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

/* Initialize Stack */
int StackAlloc(Stack *s, int max) {
    s->ptr = 0;
    // When allocating fails
    if ( (s->stk = calloc(max, sizeof(int))) == NULL ) {
        s->max = 0;
        return -1;
    }
    s->max = max;
    return 0;
}

/* Free Stack Allocation */
void StackFree(Stack *s) {
    if (s->stk != NULL) {
        free(s->stk);
        s->max = s->ptr = 0;
    }
}

/* Push Data to Stack */
int StackPush(Stack *s, int x) {
    if (s->ptr >= s->max)
        return -1;
    s->stk[s->ptr++] = x;
    return 0;
}

/* Pop Data from Stack */
int StackPop(Stack *s, int *x) {
    if (s->ptr <= 0)
        return -1;
    *x = s->stk[--s->ptr];
}

/* Peek Data from Stack */
int StackPeek(const Stack *s, int *x) {
    if (s->ptr <= 0)
        return -1;
    *x = s->stk[s->ptr - 1];
    return 0;
}

/* Size of Stack */
int StackSize(const Stack *s) {
    return s->max;
}

/* Number of Data in Stack */
int StackNo(const Stack *s) {
    return s->ptr;
}

/* Is Stack Empty */
int StackIsEmpty(const Stack *s) {
    return (s->ptr <= 0);
}

/* Is Stack Full */
int StackIsFull(const Stack *s) {
    return (s->ptr >= s->max);
}

/* Clear Stack */
void StackClear(Stack *s) {
    s->ptr = 0;
}
