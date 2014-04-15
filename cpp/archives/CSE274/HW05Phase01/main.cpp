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
    bst.insert(3, "foo");
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

    test_printPreOrder(bst);
    test_printInOrder(bst);
    test_printPostOrder(bst);
	return 0;
}
