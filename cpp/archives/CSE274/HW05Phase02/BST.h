//DO NOT CHANGE THIS FILE
//Author: Bo Brinkman
//Modified by Daniela Inclezan

#include "Dictionary.h"
#include <iostream>

using namespace std;

template <class Key, class Value>
class BSTNode {
public:
  BSTNode();
  BSTNode(Key k, Value v);

  Key k;
  Value v;
  BSTNode* left;
  BSTNode* right;
};

template <class Key, class Value>
class BST : public Dictionary<Key,Value> {
public:
  BST();
  ~BST();

  /*** 
   * Add a new entry with key k and value v.
   * If an item with key k already exists, overwrite it.
   ***/
  virtual void insert(Key k, Value v);

  /***
   * Return the value of the entry with key k. 
   * If there is no such item, throw an exception.
   ***/
  virtual Value find(Key k);

private:
  BSTNode<Key,Value>* root;

  /*** 
   * These are the recursive versions of each of your public member functions. 
   ***/
  virtual BSTNode<Key,Value>* insertHelper(BSTNode<Key,Value>* r, Key k, Value v);
  virtual BSTNode<Key,Value>* findHelper(BSTNode<Key,Value>* r, Key k);
};


#include "BST.ipp"

