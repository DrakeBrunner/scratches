#include <string>
using namespace std;

class Player {
    private:
        string name;
        int possesion;
        string blind;
    public:
        // Declaration of Constructer
        Player();
        Player(string name);
        Player(int possesion);
        Player(string name, int possesion);
        Player(string name, string blind);
        Player(int possesion, string blind);
        Player(string name, int possesion, string blind);

        void setName();
        void setName(string name);
        string getName();
        void setPos(int possesion);
        void subPos(int amount);
        void addPos(int amount);
        int getPos();
        void setBlind();
        void setBlind(string blind);
        string getBlind();
};

// Constructer
Player::Player() {
    setName();
    possesion = 0;
    setBlind();
}

Player::Player(string name) {
    setName(name);
    possesion = 0;
    setBlind();
}

Player::Player(int possesion) {
    setName();
    this->possesion = possesion;
    setBlind();
}

Player::Player(string name, int possesion) {
    setName(name);
    this->possesion = possesion;
    setBlind();
}

Player::Player(string name, string blind) {
    setName(name);
    possesion = 0;
    setBlind();
}

Player::Player(int possesion, string blind) {
    setName();
    this->possesion = possesion;
    setBlind();
}

Player::Player(string name, int possesion, string blind) {
    setName(name);
    this->possesion = possesion;
    setBlind(blind);
}
// End of Constructer

void Player::setName() {
    name = "";
}

void Player::setName(string name) {
    this->name = name;
}

string Player::getName() {
    return name;
}

void Player::setPos(int possesion) {
    this->possesion = possesion;
}

void Player::subPos(int amount) {
    if (possesion > amount)
        possesion -= amount;
    else
        cout << "Invalid amount!\n";
}

void Player::addPos(int amount) {
    possesion += amount;
}

int Player::getPos() {
    return possesion;
}

void Player::setBlind() {
    blind = "";
}

void Player::setBlind(string blind) {
    this->blind = blind;
}

string Player::getBlind() {
    return blind;
}
