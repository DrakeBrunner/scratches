#include <iostream>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Players.h"

using namespace std;

// Prototypes
void play_game();
int generate_rand();
string codeToString(int card_code, string *suit, string *number);
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

    cout << generate_rand() << '\n';
    string suit, num;
    codeToString(generate_rand(), &suit, &num);
    cout << suit << '\n' << num << '\n';

}

int generate_rand() {
    srand((unsigned int)time(NULL));

    int suit = rand() % 4;
    int number = rand() % 13 + 1;
    // See below for explanation
    int card_code = 10 * number + suit;

    return card_code;
}

void codeToString(int card_code, string *suit, string *number) {
    // Explanation of how it works;
    // card_code is something like 123, 51, 42
    // The last digit represents the suit,
    // meaning it can only be 0, 1, 2, 3
    // 0 => Spades
    // 1 => Hearts
    // 2 => Diamonds
    // 3 => Clubs
    //
    // The rest of the digits represent the number of the card.
    // For example, 123 means Queen of Clubs,
    // 51 is 5 of Hearts, 42 is 4 of Diamonds.
    int suit_code = card_code % 10;
    int number_code = (int)(card_code / 10);

    switch (suit_code) {
        case 0:
            suit = "Spades";    break;
        case 1:
            suit = "Hearts";    break;
        case 2:
            suit = "Diamonds";  break;
        case 3:
            suit = "Clubs";     break;
    }

    switch (number_code) {
        case 1:
            number = "Ace";     break;
        case 11:
            number = "Jack";    break;
        case 12:
            number = "Queen";   break;
        case 13:
            number = "King";    break;
        default:
            number = number_code;
    }
}

bool confirm() {
    string answer;
    getline(cin, answer);
    if (answer == "n" || answer == "N")
        return false;
    else
        return true;
}
