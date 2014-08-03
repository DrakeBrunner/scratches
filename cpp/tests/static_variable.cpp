#include <iostream>

class Foo {
public:
    static int i;
    int j;

    Foo() {
        j = 0;
    }
};

int Foo::i = 0;

void increment(Foo f) {
    f.i++;
    f.j++;
}

int main() {
    Foo f;

    std::cout << f.i << std::endl;  // 0
    std::cout << f.j << std::endl;  // 0

    increment(f);                   // Pass by value

    std::cout << f.i << std::endl;  // 1
    std::cout << f.j << std::endl;  // 0

    return 0;
}
