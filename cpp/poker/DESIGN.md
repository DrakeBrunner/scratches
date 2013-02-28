Poker Program
=============

Description
-----------
This is a program that allow users to play Texas Holdem'

Files
-----
*   Poker.cpp       -> Main program  

*   Player.cpp      -> Keeps track of user data  
    -string name  
    -int money  
    -string hand  
  
    +int getName(void)  
    +int getMoney(void)  
    +void addMoney(int)  
    +void setName(string)  
    +bool isBB(void)  
    +bool isSB(void)  
    +bool isDealer(void)  

*   Cards.cpp       -> Do card-related stuff (dealing, generating...)  

*   Hands.cpp       -> Check hand strength, judge winner, etc...  
    +string nameOfHand(string)
    +string checkWinner(Player[])
    -int tieBreaker(Player[])  
    +void listHands(void)  

*   Money.cpp       -> Do money-related stuff  
    -int moneyOnTable  
  
    +int getMoneyOnTable(void)  
    +void addMoneyToTable(string, int)  
    +void giveMoneyToWinner(string, int)  

To-Do
-----
Complete the UML diagram  
Reconsider the "hand" representation
Reconsider methods in Hands.cpp  
Add AI players  
