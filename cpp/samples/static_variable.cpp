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
    Foo f, b;

    std::cout << "f.i    = " << f.i << std::endl;    // 0
    // Or better yet
    std::cout << "Foo::i = " << Foo::i << std::endl; // 0
    std::cout << "f.j    = " << f.j << std::endl;    // 0

    std::cout << "Incrementing instance f" << std::endl;
    increment(f);                                    // Pass by value

    std::cout << "f.i    = " << f.i << std::endl;    // 1 (== Foo::i)
    std::cout << "f.j    = " << f.j << std::endl;    // 0

    // Modify b
    std::cout << "b.i    = " << b.i << std::endl;    // 1 (== Foo::i)
    std::cout << "b.j    = " << b.j << std::endl;    // 0

    std::cout << "Incrementing instance b" << std::endl;
    increment(b);                                    // Pass by value

    std::cout << "b.i    = " << b.i << std::endl;    // 2 (== Foo::i)
    std::cout << "b.j    = " << b.j << std::endl;    // 0

    return 0;
}
