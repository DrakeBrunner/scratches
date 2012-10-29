#include <iostream>
#include <string>
using namespace std;

// Prototype
void pointer(char *a, char *b);

int main(int argc, char const* argv[]) {
    string one = "hello";
    string two = "world";

    pointer(&one[0], &two[0]);
    return 0;
}

void pointer(char *a, char *b) {
    *a = "change";
    *b = "this";
}
