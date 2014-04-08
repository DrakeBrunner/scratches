#include <stdio.h>
#include "stack.h"
#include "queue.h"

int main(void) {
    Stack stk;
    Queue que;
    char buf[16];
    int stk_size, que_size;

    printf("Size of Stack: ");
    scanf("%s", buf);
    stk_size = atoi(buf);
    printf("Size of Queue: ");
    scanf("%s", buf);
    que_size = atoi(buf);

    if (StackAlloc(&stk, stk_size) == -1) {
        puts("Failed to Allocate Stack");
        return 1;
    }
    if (QueueAlloc(&que, que_size) == -1) {
        puts("Failed to Allocate Queue");
        return 1;
    }

    while (1) {
        int m, x;

        printf("Data Size: <stack>%d/%d, <queue>%d/%d\n",
                StackNo(&stk), StackSize(&stk), QueueNo(&que), QueueSize(&que));
        printf("Operation: ");
        printf("(1) Add (push/enqueue), (2) Remove (pop/dequeue), (0) Exit : ");
        scanf("%s", buf);
        m = atoi(buf);

        if (m == 0)
            break;

        switch (m) {
            case 1:
                    printf("Data: ");
                    scanf("%d", &x);
                    if (StackPush(&stk, x) == -1)
                        puts("  Failed to Push Data");
                    if (QueueEnque(&que, x) == -1)
                        puts("  Failed to Enqueue Data");
                    break;
            case 2:
                    if (StackPop(&stk, &x) == -1)
                        puts("  Couldn't pop Data");
                    else
                        printf("  Popped Data: %d\n", x);

                    if (QueueDeque(&que, &x) == -1)
                        puts("  Couldn't dequeue Data");
                    else
                        printf("  Dequeued Data: %d\n", x);
                    break;
        }
    }

    StackFree(&stk);
    QueueFree(&que);

    return 0;
}
