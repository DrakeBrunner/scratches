#include "KeyList.h"

#define EMPTY "\0"

KeyListNode::KeyListNode() {
    data = EMPTY;
    prev = this;
    next = this;
}

KeyList::KeyList() {
    sentinel = new KeyListNode();
}

KeyList::~KeyList() {
    clear();
    delete sentinel;
}

void KeyList::push_back(std::string s) {
    KeyListNode * old_tail = sentinel->prev;

    KeyListNode * new_tail = new KeyListNode();
    new_tail->data = s;

    new_tail->next = sentinel;
    new_tail->prev = old_tail;

    old_tail->next = new_tail;
    sentinel->prev = new_tail;
}

void KeyList::pop_back() {
    // Don't delete if only sentinel is left
    if (isEmpty())
        return;

    // Node to get deleted
    KeyListNode * old_tail = tail();

    pop(old_tail);

    delete old_tail;
}

void KeyList::pop_nth(int n) {
    KeyListNode * to_pop = getNth(n);
    pop(to_pop);
}

void KeyList::pop(KeyListNode * to_pop) {
    KeyListNode * to_pop_prev = to_pop->prev;
    KeyListNode * to_pop_next = to_pop->next;

    to_pop_prev->next = to_pop_next;
    to_pop_next->prev = to_pop_prev;
}

void KeyList::clear() {
    while (!isEmpty())
        pop_back();
}

bool KeyList::isEmpty() {
    return sentinel == sentinel->next;
}

KeyListNode * KeyList::head() {
    return (isEmpty()) ? NULL : sentinel->next;
}

KeyListNode * KeyList::tail() {
    return (isEmpty()) ? NULL : sentinel->prev;
}

KeyListNode * KeyList::getNth(int n) {
    KeyListNode * node = head();
    for (int i = 0; i < n; i++) {
        node = node->next;
        if (node == sentinel) {
            std::cerr << "Invalid n in KeyListNode * KeyList::getNth(int n)" << std::endl;
            return NULL;
        }
    }
    return node;
}

KeyListNode * KeyList::getSentinel() {
    return sentinel;
}

void KeyList::insert_after(int n, std::string data) {
    insert_after(getNth(n - 1), data);
}

void KeyList::insert_after(KeyListNode * node, std::string data) {
    KeyListNode * to_insert = new KeyListNode();
    to_insert->data = data;
    KeyListNode * to_insert_prev = node;
    KeyListNode * to_insert_next = to_insert_prev->next;

    to_insert_prev->next = to_insert;
    to_insert->prev = to_insert_prev;

    to_insert->next = to_insert_next;
    to_insert_next->prev = to_insert;
}
