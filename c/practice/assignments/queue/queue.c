#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

/* Initialize Queue */
int QueueAlloc(Queue *q, int max) {
    q->num = 0;
    // When allocating fails
    if ( (q->que = calloc(max, sizeof(int))) == NULL ) {
        q->max = 0;
        return -1;
    }
    q->max = max;
    return 0;
}

/* Free Queue Allocation */
void QueueFree(Queue *q) {
    if (q->que != NULL) {
        free(q->que);
        q->max = q->num = q->front = q->rear = 0;
    }
}

/* Enque Data */
int QueueEnque(Queue *q, int x) {
    if (q->num >= q->max)
        return -1;
    q->que[(q->rear + 1) % q->max] = x;
    q->rear = (q->rear + 1) % q->max;
    q->num++;
    return 0;
}

/* Dequeue Data */
int QueueDeque(Queue *q, int *x) {
    if (q->num <= 0)
        return -1;

    // frontが0の時に要素番号が-1になることを防ぐ
    *x = q->que[q->front % q->max];
    q->front = (q->front + 1) % q->max;
    q->num--;
    return 0;
}

/* Queue Size */
int QueueSize(const Queue *q) {
    return q->max;
}

/* Number of Data in Queue */
int QueueNo(const Queue *q) {
    return q->num;
}

/* Is Queue Empty */
int QueueIsEmpty(const Queue *q) {
    return (q->num <= 0);
}

/* Is Queue Full */
int QueueIsFull(const Queue *q) {
    return (q->num >= q->max);
}
