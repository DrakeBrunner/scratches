// Biorhythm

// http://keisan.casio.jp/has10/SpecExec.cgi
// http://www.benricho.org/nenrei/niti-conv.html
#include <stdio.h>
#include <math.h>
#include <time.h>

int time_difference();

int main(void) {
    // Days when exporting file
    int x = 0;

    /* printf("Export to file? (y/N)"); */
    /* char choice; */
    /* scanf("%c", &choice); */
    /* int export = 0; */
    /* if (choice == 'y' || choice == 'Y') */
    /*     export = 1; */


    int days_passed = time_difference();

    printf("Days passed since you were born: %d\n", days_passed);
    int physical = days_passed % 23;
    int sentiment = days_passed % 28;
    int intelligence = days_passed % 33;

    /* if (export == 1) { */
    /*     FILE *file; */
    /*     file = fopen("graph.txt", "w"); */
    /*     if (file == NULL) */
    /*         printf("Failed to open file\n"); */

    /*     for (x = -15; x <= 15; x++) { */
    /*         double p_period = sin((double)2 * M_PI * (physical + x) / 23); */
    /*         double s_period = sin((double)2 * M_PI * (sentiment + x)/ 28); */
    /*         double i_period = sin((double)2 * M_PI * (intelligence + x) / 33); */
    /*         fprintf(file, "%f %f %f\n", p_period, s_period, i_period); */

    /*     } */
    /* } */
    /* else { */
        double p_period = sin((double)2 * M_PI * (physical + x) / 23);
        double s_period = sin((double)2 * M_PI * (sentiment + x)/ 28);
        double i_period = sin((double)2 * M_PI * (intelligence + x) / 33);
        printf("Physical: %3d\nSentiment: %3d\nIntelligence: %3d\n",
                (int)(p_period * 100), (int)(s_period * 100), (int)(i_period * 100));
    /* } */

    return 0;
}

int time_difference() {
    time_t now, birth;
    //time_t now, birth, current;
    struct tm *current;
    struct tm birth_struct;

    // Set current time
    time(&now);
    current = localtime(&now);
    /* current->tm_year += 1900; */
    /* current->tm_mon++; */
    now = mktime(current);

    // Set birthday
    int birth_year, birth_month, birth_date;

    printf("Input your birthday (YYYY.MM.DD):");
    scanf("%d.%d.%d", &birth_year, &birth_month, &birth_date);

    birth_struct.tm_year = birth_year - 1900;
    birth_struct.tm_mon = birth_month - 1;
    birth_struct.tm_mday = birth_date;
    birth = mktime(&birth_struct);

    int days_passed = (int)(difftime(now, birth) / (3600 * 24));

    return days_passed;
}
