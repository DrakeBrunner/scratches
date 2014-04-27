//DO NOT CHANGE THIS FILE
//Author: Bo Brinkman

template <class Key, class Value>
class Dictionary {
 public:
  /*** 
   * Add a new entry with key k and value v.
   * If an item with key k already exists, overwrite it.
   ***/
  virtual void insert(Key k, Value v) = 0;

  /***
   * Return the value of the entry with key k. 
   * If there is no such item, throw an exception.
   ***/
  virtual Value find(Key k) = 0;
};
