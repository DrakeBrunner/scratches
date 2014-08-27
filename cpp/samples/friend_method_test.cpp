#include <iostream>

// Forward declaration
class TestClass;

class AnotherClass {
public:
    void frd(TestClass tc);
};

// TestClass
class TestClass {
public:
    TestClass() {
        i = 0;
    }

    int get_i() {
        return i;
    }

    friend void AnotherClass::frd(TestClass tc);

private:
    int i;
};

// AnotherClass
void AnotherClass::frd(TestClass tc) {
    tc.i = 4;
    std::cout << tc.get_i() << std::endl;
    tc.i = 2;
    std::cout << tc.get_i() << std::endl;
}

int main() {
    AnotherClass a;
    TestClass tc;
    a.frd(tc);

    return 0;
}
