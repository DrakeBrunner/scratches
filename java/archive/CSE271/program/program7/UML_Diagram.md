Quiz Program
============

Description
-----------
This program allows the user to take exams based on an input file.
There are 6 different types of quiz the user can take:  
one-word response, true/false, numeric response, multiple choice with a single
right answer, multiple choice with multiple right answers, and ordering.
This program was made as an assignment in a CSE class.

Classes
-------
*   Main  
    +main(args : String[]) : void  
    +readFile(fileName : String, isRandomize : boolean) : ArrayList<Problem>  

*   _Problem_  
    -question : String  
    -isRandomize : boolean  
  
    +Problem(question : String, isRandomize : boolean)  
    +Problem(question : String)  
   
    +getQuestion() : String  
    +getIsRandomize() : boolean  
    +choicesToString(choices : ArrayList<String>)  
    +toString(qNumber : int) : String  
    +(abstract) isCorrect(guess : String) : boolean  

    *   O extends Problem  
        -correctAnswer : ArrayList<String>  
        -choices : ArrayList<String>  
  
        +O(question : String, isRandomize : boolean, input : Scanner)  
  
        +getCorrectAnswer(input : Scanner) : ArrayList<String>  
        +isCorrect(guess : String) : boolean  
        +toString(qNumber : int) : String  

    *   T extends Problem  
        +T(question : String, input : Scanner)  
  
        -correctAnswer : String  

        +getCorrectAnswer(input : Scanner) : String  
        +isCorrect(guess : String) : boolean  

    *   N extends Problem  
        +N(question : String, input : Scanner)  
  
        -correctAnswer : double  

        +isCorrect(guess : String) : boolean  
        +getCorrectAnswer(input : Scanner) : double

    *   MultipleChoice extends Problem  
        +MultipleChoice(question : String, isRandomize : boolean, input : Scanner)  
  
        -correctAnswer : ArrayList<String>  
        -wrongAnswer : ArrayList<String>  
        -choices : ArrayList<String>  
  
        -getCorrectAnswer(input : Scanner) : String  
        +isCorrect(guess : String) : boolean  
        +toString(qNumber : in) : String  

    *   W extends Problem  
        +W(question : String, input : Scanner)  
  
        -correctAnswer : ArrayList<String>  
  
        -getCorrectAnswer(input : Scanner) : ArrayList<String>  
        +isCorrect(guess : String) : boolean  
