#include <iostream>
#include <algorithm>
#include <list>

int main() {
    std::list<char> l;
    l.push_back('a');
    l.push_back('b');
    l.push_back('c');

    do {
        std::list<char>::iterator it;
        for (it = l.begin(); it != l.end(); it++)
            std::cout << *it;
        std::cout << std::endl;
    } while (std::next_permutation(l.begin(), l.end()));
    return 0;
}
