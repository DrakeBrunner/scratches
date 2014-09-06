#include <iterator>
#include <iostream>
#include <string>

int main(int argc, char const* argv[]) {
    std::string line = "fooBarBaz";
    copy(line.rbegin(), line.rend(), std::ostream_iterator<char>(std::cout, "\n"));

    return 0;
}
