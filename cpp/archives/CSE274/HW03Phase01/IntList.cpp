#include "IntList.h"

IntListNode::IntListNode() {
    data = -1;
    prev = this;
    next = this;
}

void splice(IntListNode * sourceStart, IntListNode * sourceEnd, IntListNode * target) {
    // Check if sourceStart is before sourceEnd
    IntListNode * check = sourceStart;
    while (check != sourceEnd) {
        // Target can't be between start and end
        if (check == target)
            // No good
            return;

        check = check->next;

        // start and end has to belong to the same list
        if (check == sourceStart)
            // No good
            return;
    }

    // Save where the sourceStart and sourceEnd used to connect to
    IntListNode * sourceStartPrev = sourceStart->prev;
    IntListNode * sourceEndNext = sourceEnd->next;
    // Connect
    sourceStartPrev->next = sourceEndNext;
    sourceEndNext->prev = sourceStartPrev;

    // Insert sourceStart .. sourceEndNext after target
    IntListNode * target_next = target->next;

    target->next = sourceStart;
    sourceStart->prev = target;

    target_next->prev = sourceEnd;
    sourceEnd->next = target_next;
}

IntList::IntList() {
    sentinel = new IntListNode();
}

IntList::~IntList() {
    clear();
    delete sentinel;
}

void IntList::push_back(int i) {
    IntListNode * old_tail = sentinel->prev;

    IntListNode * new_tail = new IntListNode();
    new_tail->data = i;

    new_tail->next = sentinel;
    new_tail->prev = old_tail;

    old_tail->next = new_tail;
    sentinel->prev = new_tail;
}

void IntList::pop_back() {
    // Don't delete if only sentinel is left
    if (isEmpty())
        return;

    // Node to get deleted
    IntListNode * old_tail = tail();
    // Second from last node
    IntListNode * new_tail = old_tail->prev;

    new_tail->next = sentinel;
    sentinel->prev = new_tail;

    delete old_tail;
}

void IntList::clear() {
    while (!isEmpty())
        pop_back();
}

bool IntList::isEmpty() {
    return sentinel == sentinel->next;
}

IntListNode * IntList::head() {
    return (isEmpty()) ? NULL : sentinel->next;
}

IntListNode * IntList::tail() {
    return (isEmpty()) ? NULL : sentinel->prev;
}

IntListNode * IntList::getSentinel() {
    return sentinel;
}
