#include <iostream>

#include "AST.h"

using namespace std;

int main() {
    AST ast;
    ast.parse_xml("sample.xml");
    cout << ast.to_string() << endl;
    return 0;
}
