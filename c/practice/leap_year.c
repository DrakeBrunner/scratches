// Determine whether the input is a leap year or not
#include <stdio.h>

int main()
{
    int year;

    printf("Input year: ");
    scanf("%d", &year);

    if (year % 4 == 0) {
        if (year >= 100 && year % 100 == 0) {
            if (year % 400 == 0) {
                printf("Year %d is a leap year!\n", year);
            }
            else {
                printf("Year %d is not a leap year!\n", year);
            }
        }
        else {
           printf("Year %d is a leap year!\n", year);
        }
    }
    else {
        printf("Year %d is not a leap year!\n", year);
    }

    return 0;
}
