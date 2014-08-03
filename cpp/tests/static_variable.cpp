#include <iostream>

class Foo {
public:
    static int i;
};

int Foo::i = 0;

int main() {
    Foo f;

    std::cout << f.i << std::endl;  // 0

    f.i++;

    std::cout << f.i << std::endl;  // 1

    return 0;
}
