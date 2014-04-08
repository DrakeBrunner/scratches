#pragma once
// For NULL
#include <iostream>
#include <string>

class KeyListNode {
public:
    /*
     * Initialize data to -1, and also initialize prev and next.
     */
    KeyListNode();

    /* Actual data that we want to store */
    std::string data;

    /*
     * Pointers that connects this node to the privious and next nodes.
     */
    KeyListNode * prev;
    KeyListNode * next;
};

class KeyList {
public:
    KeyList();
    ~KeyList();

    /*
     * Creates a new node with data s, and adds that node to the tail of the list.
     */
    void push_back(std::string s);

    /*
     * Removes the node at the end of the list.
     */
    void pop_back();

    /* Deletes all other nodes except the sentinel */
    void clear();

    /* True if there are no other nodes than the sentinel in the list */
    bool isEmpty();
    
    KeyListNode * head();
    KeyListNode * tail();
    /*
     * Get the Nth node in the list where 0 is the head of the list.
     * Don't use this method like
     *
     *     for (int i = 0; i < n; i++) {
     *         KeyListNode node = getNth(i);
     *         ...
     *     }
     *
     * because this method iterates through the list, making the above code
     * very inefficient. Use the next instance variable instead.
     */
    KeyListNode * getNth(int n);
    KeyListNode * getSentinel();

    /*
     * Inserts a node with the given data to the Nth position of the list.
     * For example, if 3 was given as the position, the node will be inserted
     * between the currently 2nd and 3rd node.
     */
    void insert_after(int n, std::string data);
    /*
     * insert_after is the node that will be the prev of node containing data.
     * Read: "insert node containing `data` after `insert_after`"
     */
    void insert_after(KeyListNode * insert_after, std::string data);

    /*
     * Removes the Nth node from the list. This is designed to be used with
     * the insert() method.
     */
    void pop_nth(int n);

    /*
     * Pop the given KeyListNode and connect it's prev and next.
     */
    void pop(KeyListNode * to_pop);

private:
    /* The dummy node that indicates the starting point */
    KeyListNode* sentinel;
};
