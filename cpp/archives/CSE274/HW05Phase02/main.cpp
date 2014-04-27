#include <time.h>
#include <stdlib.h>
#include "BST.h"
#include <cstdio>

// #include <windows.h>
// #include <mmsystem.h>
// #pragma comment (lib, "winmm.lib")

using namespace std;

void print_result(time_t start, time_t end, long n) {
    double sec = ((end - start) / (double)CLOCKS_PER_SEC);
    printf("Time for %10ld: %7.3lf s\n", n, sec);
}

void print_find(time_t start, time_t end, long key, long found) {
    double millisec = ((end - start) / (double)CLOCKS_PER_SEC) * 1000;
    printf("Time it took to find %8ld => %-10ld: %lf millisec\n", key, found, millisec);
}

void insert_n(long n) {
    BST<long, long>* ssn = new BST<long, long>;
    time_t start, end;

    // long * keys = new long[n];
    // long * vals = new long[n];
    start = clock();
    long key, val;
    for (int i = 0; i < n; i++) {
        // Randomly generate "unique" ID (duplicate is ok)
        // keys[i] = rand() % n;
        key = rand() % n;

        // Randomly generate numbers between 100,000,000 and 999,999,999
        // vals[i] = rand() % 899999999 + 100000000;
        val = rand() % 899999999 + 100000000;

        ssn->insert(key, val);
    }

    // Measure time it takes to insert
    // start = clock();
    // for (int i = 0; i < n; i++)
    //     ssn->insert(keys[i], vals[i]);
    // end = clock();
    //
    // delete[] keys;
    // delete[] vals;

    end = clock();

    // Print result
    print_result(start, end, n);

    // Get last inserted value
    // long last_key = keys[n - 1];
    long last_key = key;
    start = clock();
    long last_val = ssn->find(last_key);
    end = clock();
    print_find(start, end, last_key, last_val);

    delete ssn;
}

int main(){
    srand(time(NULL));

    insert_n(1000000);
    insert_n(10000000);
    insert_n(100000000);
    // insert_n(1000000000);

	return 0;
}
