#include <iostream>
#include <string>
#include "BST.h"

using namespace std;

/***
 * TODO:
 * - Write separate functions to test each of the *public* methods in the BST class.
 * - In your test for insert, make sure to insert at least 10 entries into the BST.
 ***/
template<class Key, class Value>
void test_size(BST<Key, Value> & bst) {
    cout << bst.size() << endl;
}

template<class Key, class Value>
void test_insert(BST<Key, Value> & bst) {
    bst.insert(4, "foo");
    bst.insert(5, "bar");
    bst.insert(5, "baz");
    bst.insert(6, "baz");
    bst.insert(10, "baz");
    bst.insert(15, "baz");
    bst.insert(25, "baz");
    bst.insert(8, "baz");
    bst.insert(13, "baz");
    bst.insert(19, "baz");
    bst.insert(1, "baz");
    bst.insert(9, "baz");
    bst.insert(3, "baz");
    bst.insert(0, "baz");
    bst.insert(7, "baz");
}

template<class Key, class Value>
void test_find(BST<Key, Value> & bst) {
    cout << "finding key " << 3 << " " <<  bst.find(3) << endl;
    cout << "finding key " << 5 << " " <<  bst.find(5) << endl;

    // Execption
    // bst.find(4);
}

template<class Key, class Value>
void test_keyExists(BST<Key, Value> & bst) {
    cout << "key " << 3 << " " << (bst.keyExists(3) ? "exists" : "doesn't exist") << endl;
    cout << "key " << 4 << " " << (bst.keyExists(4) ? "exists" : "doesn't exist") << endl;
    cout << "key " << 15 << " " << (bst.keyExists(15) ? "exists" : "doesn't exist") << endl;
    cout << "key " << 25 << " " << (bst.keyExists(25) ? "exists" : "doesn't exist") << endl;
}

template<class Key, class Value>
void test_next(BST<Key, Value> & bst) {
    cout << "testing next" << endl;
    cout << "1 -> " << bst.next(1) << endl;
    cout << "2 -> " << bst.next(2) << endl;
    cout << "3 -> " << bst.next(3) << endl;
    cout << "4 -> " << bst.next(4) << endl;
    cout << "5 -> " << bst.next(5) << endl;
    cout << "6 -> " << bst.next(6) << endl;
    cout << "7 -> " << bst.next(7) << endl;
    cout << "9 -> " << bst.next(9) << endl;
    cout << "10 -> " << bst.next(10) << endl;
    cout << "11 -> " << bst.next(11) << endl;
    cout << "13 -> " << bst.next(13) << endl;
    cout << "15 -> " << bst.next(15) << endl;
    cout << "25 -> " << bst.next(25) << endl;
    cout << "26 -> " << bst.next(26) << endl;
}

template<class Key, class Value>
void test_prev(BST<Key, Value> & bst) {
    cout << "testing prev" << endl;
    cout << bst.prev(1) << " -> 1" << endl;
    cout << bst.prev(2) << " -> 2" << endl;
    cout << bst.prev(3) << " -> 3" << endl;
    cout << bst.prev(4) << " -> 4" << endl;
    cout << bst.prev(5) << " -> 5" << endl;
    cout << bst.prev(6) << " -> 6" << endl;
    cout << bst.prev(7) << " -> 7" << endl;
    cout << bst.prev(8) << " -> 8" << endl;
    cout << bst.prev(9) << " -> 9" << endl;
    cout << bst.prev(10) << " -> 10" << endl;
    cout << bst.prev(13) << " -> 13" << endl;
    cout << bst.prev(25) << " -> 25" << endl;
}

template<class Key, class Value>
void test_printPreOrder(BST<Key, Value> & bst) {
    bst.printPreOrder();
    cout << endl;
}

template<class Key, class Value>
void test_printInOrder(BST<Key, Value> & bst) {
    bst.printInOrder();
    cout << endl;
}

template<class Key, class Value>
void test_printPostOrder(BST<Key, Value> & bst) {
    bst.printPostOrder();
    cout << endl;
}

/***
 * TODO - main():
 * - Create an instance of a BST that has keys ranging over ints,
 *   and values ranging over string;
 * - Call each of the test functions you have written above.
 ***/
int main() {
    BST<int, string> bst;
    test_insert(bst);

    test_printPreOrder(bst);

    test_size(bst);
    test_find(bst);
    test_keyExists(bst);

    test_next(bst);
    test_prev(bst);

    test_printPreOrder(bst);
    test_printInOrder(bst);
    test_printPostOrder(bst);
	return 0;
}
