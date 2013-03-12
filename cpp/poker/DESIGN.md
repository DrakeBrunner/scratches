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

*   Hands.cpp       -> A set of Cards
    -Card[] myCards  
    -Card[] publicCards  
  
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

*   Table.cpp       -> Manage things on the table  
    -int moneyOnTable  
    -Card[] publicCards  
  
    +Card[] getPublicCards(void)  
    +void appendPublicCard(Card)  
    +void setPublicCard(Card, int)  
    +int getMoneyOnTable(void)  
    +void addMoneyToTable(string, int)  
    +void giveMoneyToWinner(string, int)  

    *   Dealer.cpp extends Table has a Card -> Do stuff that a Dealer normally does (dealing, generating...)  
        -Card[][] deckOfCards  
    
        +void collectMoney(Player, int)  
        +void dealCard(Player)  
        -Card pickRandomCard(void)  
        +Player checkWinner(Player[])  
        +void listOfHands(void)  
        +void bet(Player)  
        +void raise(Player)  
        +void allIn(Player)  
        +void fold(Player)  
        +void call(Player)  

To-Do
-----
Complete the UML diagram  
Reconsider methods in Hands.cpp  
Add AI players  
How to avoid duplicate card dealing in Dealer.cpp  
Necessity of Card[] publicCards in Hands.cpp  
Necessity of setPublicCard in Table.cpp (do we ever set Nth card?)  
Be careful about returning array reference in Table.cpp  
Maybe make a new class that does bet, raise, fold...?  
