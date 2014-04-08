#include <iostream>
using namespace std;

class IntList {
    int list[10];

    public:
        int & operator[](const int n);
};

int & IntList::operator[](const int n) {
    return list[n];
}

int main() {
    IntList l;
    l[4] = 5;
    std::cout << l[4] << std::endl;
    l[4] = 6;
    std::cout << l[4] << std::endl;
    return 0;
}
