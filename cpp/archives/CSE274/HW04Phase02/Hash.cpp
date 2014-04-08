#include <iostream>
#include <cstdlib>
#include "Hash.h"

Hash::Hash(int size, int a, int b) {
    this->size = abs(size);
    this->a = a;
    this->b = b;

    keys = new std::string[size];
    values = new int[size];

    init(size);
}

Hash::~Hash() {
    delete[] keys;
    delete[] values;
}

void Hash::init(int size) {
    for (int i = 0; i < size; i++) {
        keys[i] = EMPTY_KEY;
        values[i] = EMPTY_VALUE;
    }
}

inline int Hash::get_key(std::string s) {
    int len = s.length();

    if (len < 1)
        return -1;

    // int key = s[0] + s[len / 2] + s[len - 1];
    int key = 0;
    for (int i = 0; i < len; i++)
        key += s[i];

    return key;
}

inline int Hash::get_index(std::string key, int probe) {
    int key_int = get_key(key);

    if (probe >= size)
        return -1;

    int h1 = hash(key_int);
    int h2 = 1 + hash(key_int) % (size - 1);

    int index = (h1 + probe * h2) % size;

    return index;
}

inline int Hash::hash(int key) {
    return a * key + b;
}

void Hash::set(std::string key, int value) {
    bool is_defined = defined(key);

    // Search for empty slot
    for (int i = 0; i < size; i++) {
        int index = get_index(key, i);

        // Set keys and values if empty
        if ((!is_defined && keys[index] == EMPTY_KEY)
                    || (is_defined && keys[index] == key)) {
            keys[index] = key;
            values[index] = value;
            return;
        }
    }
}

void Hash::del(std::string key) {
    // Search for the key we're looking for
    for (int i = 0; i < size; i++) {
        int index = get_index(key, i);

        if (keys[index] == key) {
            keys[index] = EMPTY_KEY;
            values[index] = EMPTY_VALUE;
            return;
        }
    }
}

int Hash::get(std::string key) {
    for (int i = 0; i < size; i++) {
        int index = get_index(key, i);

        if (keys[index] == key)
            return values[index];
    }

    return EMPTY_VALUE;
}

bool Hash::defined(std::string key) {
    return get(key) != EMPTY_VALUE;
}
