#include "MyTrends.h"

MyTrends::MyTrends() {
    hash = new Hash(7699, 31, 53);
    // hash = new Hash(7993, 31, 67);
    entry_count = 0;
}

MyTrends::MyTrends(int size, int a, int b) {
    hash = new Hash(size, a, b);
    entry_count = 0;
}

MyTrends::~MyTrends() {
    delete hash;
}

void MyTrends::increaseCount(std::string key, int amount) {
    bool is_defined = hash->defined(key);
    int value = amount;

    // Add current value if key exists
    if (is_defined) {
        value += hash->get(key);

        // Remove current entry from list because it will be added later
        KeyListNode * node = list.head();
        while (node->data != key)
            node = node->next;
        list.pop(node);
    }
    // Increase the number of entries because key is going to be added (not
    // updated)
    else
        entry_count++;

    // Update value
    hash->set(key, value);

    // Start from back if not defined
    insert_node(key, value, !is_defined);
}

int MyTrends::getCount(std::string key) {
    return hash->get(key);
}

std::string MyTrends::getNthPopular(int n) {
    string s = list.getNth(n)->data;
    return s;
}

int MyTrends::numEntries() {
    return entry_count;
}

void MyTrends::insert_node(std::string key, int value, bool reverse = false) {
    // Insert anyway if list is empty
    if (list.isEmpty()) {
        list.insert_after(list.getSentinel(), key);
        return;
    }

    KeyListNode * node = reverse ? list.tail() : list.head();

    /*
     * Look from the first (end if going in reverse) key in the list until it
     * reaches a key with value that's less (more if reverse) than the value
     * to be inserted.
     */
    while ((!reverse && value < hash->get(node->data))
                || (reverse && value > hash->get(node->data))) {
        node = reverse ? node->prev : node->next;

        // Return if it reached the sentinel
        if (node == list.getSentinel()) {
            // Insert to last of list
            if (!reverse)
                list.insert_after(node->prev, key);
            // Insert to first of list
            else
                list.insert_after(node, key);
            return;
        }

    }

    // If the values are the same, compare the keys in the same way
    if (value == hash->get(node->data)) {
        while ((!reverse && key > node->data)
                || (reverse && key < node->data)) {
            node = reverse ? node->prev : node->next;

            // Return if it reached the sentinel or if it reaches the end
            // of the same values
            if (node == list.getSentinel()
                    || hash->get(node->data) != value) {
                if (!reverse)
                    list.insert_after(node->prev, key);
                else
                    list.insert_after(node, key);
                return;
            }
        }
    }

    // Go back one so that when insert()ing, it'll add to the appropriate
    // position
    if (!reverse)
        node = node->prev;

    list.insert_after(node, key);
}
