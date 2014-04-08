#include <stdio.h>
#include <stdlib.h>
#include "liqueue.h"
#include "node.h"

/* Initialize Queue */
int QueueInit(Queue *q) {
    q->front = NULL;
    q->rear = NULL;
    q->num = 0;
    return 0;
}

void QueueFree(Queue *q) {
    Node *ptr = q->front;
    Node *del;

    while (ptr != NULL) {
        del = ptr;
        ptr = ptr->next;
        free(del);
    }
    q->front = NULL;
    q->rear = NULL;
}

int QueueEnque(Queue *q, Node *x) {
    // ここに解答を書き加える
    // 空だったら
    if (q->rear == NULL) {
        q->front = x;
        q->rear = x;
    }
    else {
        Node *ptr = q->rear;
        // xをnextに設定する
        SetNode(ptr, ptr->no, ptr->name, x);

        q->rear = x;
    }

    q->num++;
    return q->num;
}

Node *QueueDeque(Queue *q) {
    // ここに解答を書き加える
    // すでに空だったら
    if (q->front == NULL)
        return NULL;

    Node *ptr = q->front;
    if (ptr->next != NULL)
        q->front = ptr->next;
    // もしキューが空になるなら
    else
        q->rear = q->front = NULL;

    q->num--;

    return ptr;
}

int QueueNo(const Queue *q) {
    // ここに解答を書き加える
    return q->num;
}

void PrintQueue(Queue *q) {
    Node *ptr = q->front;

    while (ptr != NULL) {
        printf("%5d %-10.10s\n", ptr->no, ptr->name);
        ptr = ptr->next;
    }
}

int QueueIsEmpty(const Queue *q) {
    return (q->num <= 0);
}
