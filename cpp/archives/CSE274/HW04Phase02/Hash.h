#pragma once
#include <string>

#define EMPTY_KEY "\0"
#define EMPTY_VALUE -1

class Hash {
public:
    /*
     * Creates arrays of keys and values for with the given size.
     * a and b are used to figure out the hash value.
     */
    Hash(int size, int a, int b);
    ~Hash();

    /*
     * Hash method that returns the index of the array for a given integer
     * key.
     */
    int hash(int key);

    /*
     * Creates or updates the entry in the hash table for the given key and value.
     */
    void set(std::string key, int value);

    /*
     * Removes the entry for the given key.
     */
    void del(std::string key);

    /*
     * Accesses the hash table and returns the value for the given key.
     */
    int get(std::string key);

    /*
     * Checks whether the given key exists in the hash table or not.
     */
    bool defined(std::string key);

private:
    /*
     * Initializes the keys and values arrays to EMPTY.
     */
    void init(int size);

    /*
     * Calculates the index from the given key. This method calculates the
     * index using the following formula (double hashing):
     *
     *     index = hash(key) + i * hash2(key) % size
     * where
     *     hash2(key) = 1 + (hash(key) % (size - 1))
     *
     * This method is typically called inside a for loop.
     * Returns -1 when the probe is invalid (probe >= size).
     */
    int get_index(std::string key, int probe);

    /*
     * Calculates the key from the given string.
     * This method sums the ASCII code of the first, last, and the middle
     * character of the string.
     */
    int get_key(std::string s);

    /* The size of the hash table */
    int size;

    /*
     * Values used in the hash method. These must be prime numbers.
     */
    int a, b;

    std::string * keys;
    int * values;
};
