#include <iostream>
#include <cstring>
#include <string>
using namespace std;

int main(int argc, char const* argv[]) {
    string str = "hello";
    if (str == "hello")
        cout << "good" << endl;

    if (strcmp(str.c_str(), "hello") == 0)
        cout << "c_str" << endl;

    const char * c = "foobar";
    if (strcmp(c, "foobar") == 0)
        cout << "strcmp" << endl;
    return 0;
}
