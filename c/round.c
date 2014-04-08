//This is a program that displays what the rounded number is when divided into doubles.

#include <stdio.h>

int main()
{
  int a, b, n;
  float a2, b2, f;

  printf("Enter Number A: ");
  scanf("%d", &a);

  printf("Enter Number B: ");
  scanf("%d", &b);


  if(a * b < 0){
    puts("Please retry with a positive integer");
  }

  a2 = a;
  b2 = b;

  n = a / b;      //Calculates the number without "."
  f = a2 / b2;      //Calculates the number correctly

  printf("The rounded number is: ");

  if((f - n) >= 0.5){
    printf("%d\n", ++n);
    }else{
    printf("%d\n", n);
  }

  return 0;
}
