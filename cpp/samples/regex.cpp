#include <iostream>
#include <regex>
#include <string>

void test_capture(std::string str) {
    std::regex r("(move|pile) (\\d+) (onto|over) (\\d+)");
    std::smatch match;

    std::regex_search(str, match, r);

    // Entire match
    std::cout << match[0] << std::endl;
    // Captures
    std::cout << match[1] << std::endl;
    std::cout << match[2] << std::endl;
    std::cout << match[3] << std::endl;
    std::cout << match[4] << std::endl;
}

int main() {
    std::string str = "move 5 onto 3";

    test_capture(str);
    return 0;
}
