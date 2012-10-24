#include <iostream>
#include <string>
#include <stdio.h>
#include <time.h>
#include "Players.h"

using namespace std;

// Prototypes
void play_game();
int pickCard();
bool confirm();

int main(int argc, char const* argv[])
{
    string answer;
    while (true) {
        play_game();

        cout << "Continue game?(y/N) ";
        getline(cin, answer);
        if (answer == "y" || answer == "Y")
            continue;
        else
            break;
    }

    return 0;
}

void play_game() {
    // Ask and confirm player number and initial possesion
    int player_num = 0;
    int initial_possesion;
    while (true) {
        cout << "Please enter the number of players: ";
        cin >> player_num;
        cin.ignore();

        cout << "What will the initial money be? ";
        cin >> initial_possesion;
        cin.ignore();

        // Confirm
        printf("Are the number of players (%d) and initial money (%d) correct? (Y/n) ", player_num, initial_possesion);
        if (confirm() == true)
            break;
    }

    // Array for user data
    Player *players = new Player[player_num];
    // Input user data
    for (int i = 0; i < player_num; i++) {
        cout << "Player No. " << i + 1 << '\n';
        // Set name
        cout << "Name? ";
        string name;
        getline(cin, name);
        players[i].setName(name);
        // Set possesion to default value
        players[i].setPos(initial_possesion);
        // Set blind
        cout << "Blind? (SB/BB/Dealer) ";
        string blind;
        getline(cin, blind);
        players[i].setBlind(blind);

        // Confirm
        cout << "Player No. " << i + 1 << '\n';
        cout << "  Name:\t\t" << players[i].getName() << '\n';
        cout << "  Possesion:\t" << players[i].getPos() << '\n';
        cout << "  Blind:\t" << players[i].getBlind() << "\n\n";

        cout << "Is this correct? (Y/n) ";
        if (confirm() == false)
            // Redo loop if answer is no
            i--;
    }

    Cards card = new Cards;
    int[0] result_tmp = card.pickCard();
    cout << result_tmp[0] << ", " << result_tmp[1] << '\n';

}

int generate_rand(int limit) {
    srand( (unsigned int)time(NULL) );

    return rand() % limit + 1;
}

bool confirm() {
    string answer;
    getline(cin, answer);
    if (answer == "n" || answer == "N")
        return false;
    else
        return true;
}
