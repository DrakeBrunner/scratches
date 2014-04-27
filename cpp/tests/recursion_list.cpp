#include <iostream>
#include <list>
using namespace std;

void recursive_func(list<int>* l) {
    int in;
    cin >> in;

    if (in == -1)
        return;

    // Crash
    l->push_back(in);
    recursive_func(l);
}

int main() {
    list<int>* l;

    recursive_func(l);

    return 0;
}
