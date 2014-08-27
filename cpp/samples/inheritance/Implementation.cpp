#include <iostream>
#include "Base.h"
#include "Derived.h"
using namespace std;

Base::Base() {
    cout << "base constructor called" << endl;
}

// Having " : Base()" and not having it doesn't make any difference
Derived::Derived() : Base() {
    cout << "Derived constructor called" << endl;
}

void Base::method1() {
    cout << "method1 in base called" << endl;
}

// void Derived::method1() {
//     cout << "method1 in derived called" << endl;
// }

int main() {
    cout << "starting base" << endl;
    Base b = Base();
    cout << "starting derived" << endl;
    Derived d = Derived();

    cout << "testing method overriding" << endl;
    b.method1();
    cout << "and for method1 in Derived" << endl;
    d.method1();

    cout << "specify method1 in base" << endl;
}
