#include <iostream>
#include "IntList.h"

void testInstantiation() {
    IntList l;
}

void printNode(IntListNode * node) {
    std::cout << "Address: " << node << std::endl;
    std::cout << "Data: " << node->data << std::endl;
}

void testPopPush() {
    IntList * list = new IntList();
    IntListNode * s = list->getSentinel();
    // Sentinel
    printNode(s);

    // Add one
    list->push_back(5);
    printNode(list->tail());

    // Add another
    list->push_back(4);
    printNode(list->tail());

    // Pop last
    list->pop_back();
    printNode(list->tail());

    // Pop another
    list->pop_back();
    if (list->tail() != NULL)
        printNode(list->tail());

}

void testSplice() {
    IntList * list = new IntList();

    list->push_back(1);
    list->push_back(2);
    list->push_back(3);
    list->push_back(4);
    list->push_back(5);
    list->push_back(6);

    // 3
    IntListNode * a = list->head()->next->next;
    // 5
    IntListNode * b = list->tail()->prev;
    // 4 (Invalid target)
    // IntListNode * t = list->head()->next->next->next;
    // 1
    IntListNode * t = list->head();

    splice(a, b, t);

    IntListNode * node = list->getSentinel();
    do {
        node = node->next;
        printNode(node);
    } while (node != list->getSentinel());
}

int main() {
    testInstantiation();
    testPopPush();
    std::cout << "testing splice" << std::endl;
    testSplice();
    return 0;
}
