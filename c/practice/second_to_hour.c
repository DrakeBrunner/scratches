/* This program displays the input second time as hours, minutes, seconds */
#include <stdio.h>

int time, hour, min, sec, redo;

int main () {
    /* Initialize for the first loop */
    redo = 1;

    while (redo) {
        convert();
        /* Ask if redo */
        printf ("Redo? (1 for yes, 0 for no): ");
        scanf ("%d", &redo);
    }

    return 0;
}

int convert(void)
{
    printf ("Input time in seconds: ");
    scanf ("%d", &time);

    /* In case time is smaller than 60 */
    sec = time;

    if (time >= 60) {
        min = time / 60;
        sec = time % 60;
    }

    if (min >= 60) {
        hour = min / 60;
        min %= 60;
    }

    if (hour >= 1) {
        printf ("\n%d hours", hour);
    }
    if (min >= 1) {
        printf (" %d minutes", min);
   }
    if (sec > 0) {
        /* Process when hour and/or min is more than 0 */
        if (hour > 0 || min > 0) {
            printf (" and ");
        }
        printf ("%d seconds", sec); 
    }

    puts (".");

    return;
}
