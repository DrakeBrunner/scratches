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
        Cards(int quantity);

        int[] pickCard();
        int[] pickCard(int quantity);
};

int[] pickCard() {
    int suit = generate_rand(4);
    int number = generate_rand(13);

    int[] card = {suit, number,};
    return card;
}

int[] pickCard(int quantity) {
    int[] cards = new int[quantity];

    for (int i = 0; i < quantity; i++) {
        cards[i] = pickCard();
    }

    return cards;
}

int generate_rand(int limit) {
    srand( (unsigned int)time(NULL) );

    return rand() % limit + 1;
}
