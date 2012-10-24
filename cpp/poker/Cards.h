#include <iostream>
#include <time.h>
#include <string>
using namespace std;

class Cards {
    private:
        int suit;
        int number;
        int limit;
        int generate_rand(int limit);

    public:
        Cards();

        int pickCard();
};

// Constructer
Cards::Cards() {
    pickCard();
}

int pickCard() {
    int suit = generate_rand(4);
    int number = generate_rand(13);

    return suit, number;
}

int generate_rand(int limit) {
    srand( (unsigned int)time(NULL) );

    return rand() % limit + 1;
}
