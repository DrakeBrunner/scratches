#include <iostream>
#include <string>
#include "Players.h"

using namespace std;

// Prototypes
void play_game();
template <class confirm_this> bool confirm( confirm_this data );

int main(int argc, char const* argv[])
{
    string answer;
    while (true) {
        play_game();

        cout << "Continue game?(y/N) ";
        cin >> answer;
        if (answer == "y" || answer == "Y")
            continue;
        else
            break;
    }

    return 0;
}

void play_game() {
    int player_num = 0;
    while (true) {
        cout << "Please enter the number of players: ";
        cin >> player_num;

        if (confirm(player_num) == true)
            break;
    }

    Player naoki = new Player("naoki", 1000, "BB");
}

template <class confirm_this> bool confirm( confirm_this data ) {
    cout << "Is this correct?(Y/n) " << data << '\n';

    string answer;
    cin >> answer;
    if (answer == "n" || answer == "N")
        return false;
    else
        return true;
}
