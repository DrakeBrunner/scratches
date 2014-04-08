#include <stdio.h>
#include "llist.h"
#include "llist-plus.h"

/* Menu */
typedef enum {
    Term, Insert, InsertNth, Append,
    Delete, DeleteNth, Clear, Print
} Menu;

/* Data input */
Node Read(char *message) {
    Node temp;

    printf("Enter data to %s\n", message);

    printf("Number: ");
    scanf("%d", &temp.no);
    printf("Name: ");
    scanf("%s", temp.name);

    return temp;
}

/* Select from Menu */
Menu SelectMenu(void) {
    int ch;

    do {
        puts("1. Insert node to front");
        puts("2. Insert node to specified place");
        puts("3. Append node to last");
        puts("4. Delete first node");
        puts("5. Delete node at specified place");
        puts("6. Delete all nodes");
        puts("7. Display list of nodes");
        puts("0. Quit");

        scanf("%d", &ch);
    } while (ch < Term || ch > Print);

    return (Menu)ch;
}

/* Main */
int main(void) {
    Menu menu;
    Node *list;

    InitList(&list);

    do {
        Node x;
        int n;
        char name[NAMELEN];

        switch (menu = SelectMenu()) {
            case Insert:    x = Read("Insert");
                            InsertNode(&list, x.no, x.name);
                            break;
            case InsertNth: x = Read("Insert");
                            printf("Where? ");
                            scanf("%d", &n);
                            // Use it here
                            InsertNodeNth(&list, n, x.no, x.name);
                            break;
            case Append:    x = Read("Insert");
                            AppendNode(&list, x.no, x.name);
                            break;
            case Delete:    DeleteNode(&list);
                            break;
            case DeleteNth: printf("Where? ");
                            scanf("%d", &n);
                            // Use it here
                            DeleteNodeNth(&list, n);
                            break;
            case Clear:     ClearList(&list);
                            break;
            case Print:     PrintList(&list);
                            break;
            case Term:      break;
        }
    } while (menu != Term);

    TermList(&list);

    return 0;
}
