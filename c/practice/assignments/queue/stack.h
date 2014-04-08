/* stack.h */
#ifndef _stack
#define _stack

/* Structure */
typedef struct {
    int max;    // Stack size
    int ptr;    // Pointer of Stack
    int *stk;   // Pointer of the first element of stack
} Stack;

/* Prototypes */
int StackAlloc(Stack *s, int max);      // Initialize Stack
void StackFree(Stack *s);               // Free Stack
int StackPush(Stack *s, int x);         // Push data
int StackPop(Stack *s, int *x);         // Pop data
int StackPeek(const Stack *s, int *x);  // Peek Stack
int StackSize(const Stack *s);          // Size of Stack
int StackNo(const Stack *s);            // Number of data stored in Stack
int StackIsEmpty(const Stack *s);       // Is Stack empty
int StackIsFull(const Stack *s);        // Is Stack full
void StackClear(Stack *s);              // Clear Stack

#endif
