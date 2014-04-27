#include <string>
#include <cstddef>

// #define NULL 0

template <class Key, class Value>
BSTNode<Key,Value>::BSTNode(){
  left = NULL;
  right = NULL;
}

template <class Key, class Value>
BSTNode<Key,Value>::BSTNode(Key kk, Value vv){
  k = kk;
  v = vv;
  left = NULL;
  right = NULL;
}




template <class Key, class Value>
BST<Key,Value>::BST(){
  root = NULL;
}



template <class Key, class Value>
BST<Key,Value>::~BST(){
  // root = NULL;
  delete root;
}
  

/*** 
 * Add a new entry with key k and value v.
 * If an item with key k already exists, overwrite it.
 ***/
template <class Key, class Value>
void BST<Key,Value>::insert(Key k, Value v){
  root = insertHelper(root, k, v);
}

template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::insertHelper(BSTNode<Key,Value>* r, Key k, Value v){
  if (r == NULL) {
	  r = new BSTNode<Key,Value>(k,v);
	  return r;
  } else if (r->k > k) {
	  r->left = insertHelper(r->left, k, v);
	  return r;
  } else if (r->k < k) {
	  r->right = insertHelper(r->right, k, v);
	  return r;
  } 
  r->v = v;
  return r;
}




/***
 * Return the value of the entry with key k. 
 * If there is no such item, throw an exception.
 ***/
template <class Key, class Value>
Value BST<Key,Value>::find(Key k){
    BSTNode<Key, Value>* temp = NULL;
    try {
		temp = findHelper(root, k);
	}
	catch (int e) {
		cout << "Error " << e << endl; 
		exit(1);
	}

	return temp->v;
}


template <class Key, class Value>
BSTNode<Key,Value>* BST<Key,Value>::findHelper(BSTNode<Key,Value>* r, Key k){
  if (r == NULL) {
	  cout << "Key " <<  k << " was NOT FOUND." << endl;
	  throw 10;
  } else if (r->k > k) {
	  return findHelper(r->left, k);
  } else if (r->k < k) {
	  return findHelper(r->right, k);
  } 
  return r;
}
