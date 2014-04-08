#pragma once
#include "Trends.h"
#include "Hash.h"
#include "KeyList.h"
#include <string>

class MyTrends : public Trends {
public:
    MyTrends();
    MyTrends(int size, int a, int b);
    ~MyTrends();

    /* See Trends.h for docs */
    void increaseCount(std::string s, int amount);
    int getCount(std::string s);
    std::string getNthPopular(int n);
    int numEntries();

    /*
     * Finds the index to insert in the list for the given key. Starts from
     * the end when reverse is true.
     * This method looks at each key in the list, assuming that it's sorted
     * in the descending order of values, and tries to find the place where
     * the given key is supposed to go to. If multiple keys have the same
     * value, the method will add the key in alphabetically order.
     */
    void insert_node(std::string key, int value, bool reverse);
private:
    Hash * hash;
    /* Stores the keys sorted in the order of most popular to least popular */
    KeyList list;
    int entry_count;
};
