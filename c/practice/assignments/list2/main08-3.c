#include <stdio.h>
#include <stdlib.h>
#include "liqueue.h"

int main(void) {
    Queue que;
    char op[5];

    QueueInit(&que);    /* Initialize Queue */
    // Check Queue content
    if (!QueueIsEmpty(&que)) {
        printf("Initialization Failed\n");
        return -1;
    }
    while (1) {
        int m, no;
        char name[NAMELEN];
        Node *data;

        printf("\nOperation:\n");
        printf("  1. Enqueue\n");
        printf("  2. Dequeue\n");
        printf("  0. Exit\n");
        scanf("%s", op);
        m = atoi(op);

        if (m == 0)
            break;

        switch (m) {
            case 1:
                data = AllocNode();
                printf("Enter data to Add\n");
                printf("  Number: ");
                scanf("%d", &no);
                printf("  Name: ");
                scanf("%s", name);
                SetNode(data, no, name, NULL);
                QueueEnque(&que, data);
                // Check queue content
                if (QueueIsEmpty(&que)) {
                    printf("Failed to Enqueue\n");
                    return -1;
                }
                break;
            case 2:
                if ((data = QueueDeque(&que))) {
                    printf("Data Dequed\n");
                    printf("  Number: %d, Name: %s\n", data->no, data->name);
                    free(data);
                }
                else
                    printf("Failed to Deque\n");
                break;
        }
        printf("\nList of Queue <%d>\n", QueueNo(&que));
        PrintQueue(&que);
    }

    QueueFree(&que);

    return 0;
}
