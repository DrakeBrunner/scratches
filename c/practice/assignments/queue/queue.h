/* queue.h */

#ifndef _queue
#define _queue
/* Structure for Queue */
typedef struct {
    int max;    // Queue size
    int num;    // Current number of elements
    int front;  // Index of the first element
    int rear;   // Index of the last element
    int *que;   // Pointer of the first element
} Queue;

/* Prototypes */
int QueueAlloc(Queue *q, int max);  // Initialize Queue
void QueueFree(Queue *q);           // Free queue
int QueueEnque(Queue *q, int x);    // Enqueue data
int QueueDeque(Queue *q, int *x);   // Dequeue data
int QueueSize(const Queue *q);      // Size of Queue
int QueueNo(const Queue *q);      // Number of data stored in Queue
int QueueIsEmpty(const Queue *q);   // Is queue empty
int QueueIsFull(const Queue *q);    // Is queue full

#endif
