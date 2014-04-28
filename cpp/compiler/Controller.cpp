#include <iostream>

#include "AST.h"

using namespace std;

int main() {
    AST ast("sample.xml");

    // std::cout << "Parsed AST" << std::endl;
    // cout << ast.to_string() << endl;

    // std::cout << "Compile" << std::endl;
    cout << ast.compile() << endl;
    return 0;
}
