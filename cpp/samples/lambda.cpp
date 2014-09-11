#include <iostream>
#include <typeinfo>
#include <cstdio>

void test_capture_nothing() {
    std::cout << "\nTEST capture nothing" << std::endl;

    []() {
        std::cout << "hi!" << std::endl;
    }();
}

void test_capture_by_value() {
    std::cout << "\nTEST capture by value" << std::endl;

    int foo = 21;
    printf("BEFORE %d\n", foo);
    [=]() {
        /* Compilation error */
        // foo = 42;
    }();
    printf("AFTER %d\n", foo);
}

void test_capture_by_value_pointer() {
    std::cout << "\nTEST capture by value pointer" << std::endl;

    int* foo = new int;
    *foo = 21;
    printf("BEFORE %d\n", *foo);
    [=]() {
        *foo = 42;
    }();
    printf("AFTER %d\n", *foo);

    delete(foo);
}

void test_capture_by_reference() {
    std::cout << "\nTEST capture by reference" << std::endl;

    int foo = 3;
    printf("BEFORE %d\n", foo);
    [&]() {
        foo = 42;
    }();
    printf("AFTER %d\n", foo);
}

int main() {
    test_capture_nothing();
    test_capture_by_value();
    test_capture_by_value_pointer();
    test_capture_by_reference();

    return 0;
}
