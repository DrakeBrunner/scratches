#include <string>
using namespace std;

class Player {
    private:
        string Name;
        int Possesion;
        string Blind;
    public:
        // Constructer
        Player(string name, int amount, string blind);

        void setName(string name);
        string getName();
        void subPos(int amount);
        void addPos(int amount);
        int getPos();
        void setBlind();
        void setBlind(string blind);
        string getBlind();
};

void Player::setName(string name) {
    Name = name;
}

string Player::getName() {
    return Name;
}

void Player::subPos(int amount) {
    if (Possesion > amount)
        Possesion -= amount;
    else
        cout << "Invalid amount!\n";
}

void Player::addPos(int amount) {
    Possesion += amount;
}

int Player::getPos() {
    return Possesion;
}

void Player::setBlind() {
    Blind = "";
}

void Player::setBlind(string blind) {
    Blind = blind;
}

string Player::getBlind() {
    return Blind;
}
