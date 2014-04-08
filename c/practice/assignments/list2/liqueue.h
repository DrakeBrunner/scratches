#ifndef _liqueue
#define _liqueue

#include "node.h"

/* Structure */
typedef struct {
    int num;        /* Number of elements */
    Node *front;    /* Pointer to First element */
    Node *rear;     /* Pointer to last element */
} Queue;

int QueueInit(Queue *q);            /* Initialize Queue */
void QueueFree(Queue *q);           /* Clear Queue */
int QueueEnque(Queue *q, Node *x);  /* Enque */
Node *QueueDeque(Queue *q);         /* Deque */
int QueueNo(const Queue *q);        /* Number of elements in Queue */
void PrintQueue(Queue *q);          /* Print Queue */
int QueueIsEmpty(const Queue *q);   /* Is Queue Empty? */

#endif
