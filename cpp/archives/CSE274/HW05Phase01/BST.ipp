#include <iostream>
#include <string>
#include <stdexcept>

// THIS IS BAD!!!!!!! (and useless)
// #define NULL 0

template <class Key, class Value>
BSTNode<Key,Value>::BSTNode() {
    left = NULL;
    right = NULL;
}

template <class Key, class Value>
BSTNode<Key,Value>::BSTNode(Key k, Value v) {
    this->k = k;
    this->v = v;

    this->left = NULL;
    this->right = NULL;
}




template <class Key, class Value>
BST<Key,Value>::BST() {
    root = NULL;
}



template <class Key, class Value>
BST<Key,Value>::~BST() {
    delete root;
}


/***
 * Return the number of items currently in the Dictionary.
 ***/
template <class Key, class Value>
unsigned long BST<Key,Value>::size() {
    return sizeHelper(root);
}

template <class Key, class Value>
unsigned long BST<Key,Value>::sizeHelper(BSTNode<Key,Value>* r) {
    // When no node is present
    if (r == NULL)
        return 0;

    // Count this node
    int size = 1;

    if (r->left != NULL)
        size += sizeHelper(r->left);
    if (r->right != NULL)
        size += sizeHelper(r->right);

    return size;
}



/*** 
 * Add a new entry with key k and value v.
 * If an item with key k already exists, overwrite it.
 ***/
template <class Key, class Value>
void BST<Key,Value>::insert(Key k, Value v) {
    root = insertHelper(root, k, v);
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::insertHelper(BSTNode<Key,Value>* r, Key k, Value v) {
    if (r == NULL)
        r = new BSTNode<Key, Value>(k, v);
    else if (k < r->k)
        r->left = insertHelper(r->left, k, v);
    else if (r->k < k)
        r->right = insertHelper(r->right, k, v);
    else
        r->v = v;
    return r;
}



/***
 * Remove the entry with key k. 
 * If there is no such item, do nothing.
 ***/
template <class Key, class Value>
void BST<Key,Value>::remove(Key k) {
    root = removeHelper(root, k);
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::removeHelper(BSTNode<Key,Value>* r, Key k) {
    if (r == NULL)
        return NULL;

    if (r->k == k) {
        if (r->left != NULL) {
            // Copy the value of the maximun in the left subtree
            BSTNode<Key, Value> * max_node = max(r->left);
            r->k = max_node->k;
            r->v = max_node->v;

            r->left = removeHelper(max_node, max_node->k);
        }
        // If there is no left node
        else if (r->right != NULL)  {
            // Copy the values of the right node
            r->k = r->right->k;
            r->v = r->right->v;
            r->left = r->right->left;
            r->right = r->right->right;
        }
        // If this is a leaf
        else
            r = NULL;

    }
    else if (r->k < k)
        r->right = removeHelper(r->right, k);
    else
        r->left = removeHelper(r->left, k);

    return r;
}



/***
 * Return the value of the entry with key k. 
 * If there is no such item, throw an exception.
 ***/
template <class Key, class Value>
Value BST<Key,Value>::find(Key k) {
    BSTNode<Key, Value> * found = findHelper(root, k);
    if (found == NULL)
        throw std::invalid_argument("given key not found in tree");

    return found->v;
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::findHelper(BSTNode<Key,Value>* r, Key k) {
    if (r == NULL)
        return NULL;
    else if (k < r->k)
        return findHelper(r->left, k);
    else if (r->k < k)
        return findHelper(r->right, k);
    else
        return r;
}



/***
 * Return true if there is an entry with key k in the dictionary. 
 * If not, return false.
 ***/
template <class Key, class Value>
bool BST<Key,Value>::keyExists(Key k) {
    return findHelper(root, k) != NULL;
}



/***
 * If there is a key in the set that is > k, return the first such key. 
 * If not, return k.
 ***/
template <class Key, class Value>
Key BST<Key,Value>::next(Key k) {
    BSTNode<Key, Value> * found = nextHelper(root, k);

    // If given key is max
    if (max(root)->k <= k)
        return k;

    return (found == NULL) ? k : found->k;
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::nextHelper(BSTNode<Key,Value>* r, Key k) {
    if (r == NULL)
        return NULL;

    if (k < r->k) {
        if (r->left == NULL)
            return r;
        else if (max(r->left)->k > k)
            return nextHelper(r->left, k);
        else
            return r;
    }
    else
        return nextHelper(r->right, k);
}



/***
 * If there is a key in the set that is < k, return the first such key. 
 * If not, return k.
 ***/
template <class Key, class Value>
Key BST<Key,Value>::prev(Key k) {
    BSTNode<Key, Value> * found = prevHelper(root, k);

    if (min(root)->k >= k)
        return min(root)->k;

    return (found == NULL) ? k : found->k;
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::prevHelper(BSTNode<Key,Value>* r, Key k) {
    if (r == NULL)
        return NULL;

    if (r->k < k) {
        if (r->right == NULL)
            return min(r);
        else if (min(r->right)->k < k)
            return prevHelper(r->right, k);
        else
            return r;
    }
    else
        return prevHelper(r->left, k);
}




/***
 * The following additional functions print the tree using
 * pre-order, in-order, and post-order traversals, respectively.
 * Print each entry r using the statement:
 *   cout << "(" << r->k << "," << r->v << ") ";
 ***/

template <class Key, class Value>
void BST<Key,Value>::printPreOrder() {
    printPreOrderHelper(root);
}

template <class Key, class Value>
void BST<Key,Value>::printInOrder() {
    printInOrderHelper(root);
}

template <class Key, class Value>
void BST<Key,Value>::printPostOrder() {
    printPostOrderHelper(root);
}

template <class Key, class Value>
void BST<Key,Value>::printPreOrderHelper(BSTNode<Key,Value>* r) {
    if (r == NULL)
        return;

    cout << "(" << r->k << "," << r->v << ") ";
    printPreOrderHelper(r->left);
    printPreOrderHelper(r->right);
}

template <class Key, class Value>
void BST<Key,Value>::printInOrderHelper(BSTNode<Key,Value>* r) {
    if (r == NULL)
        return;

    // Print left subtree
    if (r->left != NULL)
        printInOrderHelper(r->left);

    cout << "(" << r->k << "," << r->v << ") ";

    if (r->right != NULL)
        printInOrderHelper(r->right);
}

template <class Key, class Value>
void BST<Key,Value>::printPostOrderHelper(BSTNode<Key,Value>* r) {
    if (r == NULL)
        return;

    // Print left subtree
    if (r->left != NULL)
        printInOrderHelper(r->left);

    if (r->right != NULL)
        printInOrderHelper(r->right);

    cout << "(" << r->k << "," << r->v << ") ";
}

/***
 * Find the item in the sub-tree rooted at r which has the smallest key.
 ***/
template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::min(BSTNode<Key,Value>* r) {
    if (r == NULL)
        return NULL;

    BSTNode<Key, Value> * node = r;

    // Go to the left-most node
    while (node->left != NULL)
        node = node->left;
    return node;
}

/*** 
 * Find the item in the sub-tree rooted at r which has the largest key.
 ***/
template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::max(BSTNode<Key,Value>* r) {
    if (r == NULL)
        return NULL;

    BSTNode<Key, Value> * node = r;

    // Go to the right-most node
    while (node->right != NULL)
        node = node->right;
    return node;
}
