#include <iostream>
#include <algorithm>
#include <list>

bool is_odd(int n) {
    return n % 2 == 1;
}

void test_list(std::list<int>& l) {
    if (std::any_of(l.begin(), l.end(), is_odd)) {
        std::cout << "AT LEAST ONE IS ODD" << std::endl;
    }
    else {
        std::cout << "NONE IS ODD" << std::endl;
    }
}

int main() {
    std::list<int> l;

    l.push_back(0);
    l.push_back(10);
    l.push_back(4);
    l.push_back(2);
    l.push_back(5);     // Odd number here

    test_list(l);

    /* Remove that odd number */
    l.pop_back();

    test_list(l);

    return 0;
}
