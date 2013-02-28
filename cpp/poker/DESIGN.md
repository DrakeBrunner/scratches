Poker Program
=============

Description
-----------
This is a program that allows users to play Texas Holdem'

Files
-----
*   Poker.cpp       -> Main program  
    +Player[] players

*   Player.cpp      -> Keeps track of user data  
    -string name  
    -int money  
    -Hands hand  
  
    +int getName(void)  
    +int getMoney(void)  
    +void addMoney(int)  
    +void setName(string)  
    +bool isBB(void)  
    +bool isSB(void)  
    +bool isDealer(void)  
    +string toString(void)  

*   Dealer.cpp       -> Do stuff that a Dealer normally does (dealing, generating...)  
    -Card[][] deckOfCards  
  
    +void collectMoney(Player, int)  
    +void dealCard(Player)  
    -Card pickRandomCard(void)  
    +Player checkWinner(Player[])  
    +void listOfHands(void)  

*   Hands.cpp       -> Check hand strength, judge winner, etc...  
    -Card[] myCards  
  
    +string nameOfHand(string)  
    -int tieBreaker(Player[])  
    +string toString(void)  

    *   Card.cpp    -> Represents one card
        -int suit  
        -int rank  
  
        +int getSuit(void)  
        +int getRank(void)  
        +string getStringSuit(void)  
        +string getStringRank(void)  
        +void setSuit(int)  
        +void setRank(int)  
        +string toString(void)  

*   TableMoney.cpp       -> Manage money on the table
    -int moneyOnTable  
  
    +int getMoneyOnTable(void)  
    +void addMoneyToTable(string, int)  
    +void giveMoneyToWinner(string, int)  

To-Do
-----
Complete the UML diagram  
Reconsider methods in Hands.cpp  
Add AI players  
How to avoid duplicate card dealing in Dealer.cpp  
Bet, raise, fold...  
