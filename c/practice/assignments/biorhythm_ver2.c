// Biorhythm

// http://keisan.casio.jp/has10/SpecExec.cgi
// http://www.benricho.org/nenrei/niti-conv.html
//
// When plotting with gnuplot, use command
//      plot "./graph.txt" using 1:2 with lines,\
//      "./graph.txt" using 1:3 with lines,\
//      "./graph.txt" using 1:4 with lines
#include <stdio.h>
#include <math.h>
#include <time.h>

int time_difference();

int main(void) {
    // Days when exporting file
    int x = 0;

    int days_passed = time_difference();

    printf("Days passed since you were born: %d\n", days_passed);

    int physical = days_passed % 23;
    int sentiment = days_passed % 28;
    int intelligence = days_passed % 33;

    // Result for today
    double p_period = sin((double)2 * M_PI * (physical + x) / 23);
    double s_period = sin((double)2 * M_PI * (sentiment + x)/ 28);
    double i_period = sin((double)2 * M_PI * (intelligence + x) / 33);
    printf("Physical: %3d\nSentiment: %3d\nIntelligence: %3d\n",
            (int)(p_period * 100), (int)(s_period * 100), (int)(i_period * 100));

    // Ask if export to file
    printf("Export to file? (y/N) ");
    char choice;
    scanf("%c", &choice);
    if (choice == 'y' || choice == 'Y') {
        FILE *file;
        file = fopen("graph.txt", "w");
        if (file == NULL)
            printf("Failed to open file\n");

        for (x = -15; x <= 15; x++) {
            double p_period = sin((double)2 * M_PI * (physical + x) / 23);
            double s_period = sin((double)2 * M_PI * (sentiment + x)/ 28);
            double i_period = sin((double)2 * M_PI * (intelligence + x) / 33);
            fprintf(file, "%d %f, %f, %f\n", x, p_period, s_period, i_period);
        }
    }

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
    // Flush buffer
    getchar();

    birth_struct.tm_year = birth_year - 1900;
    birth_struct.tm_mon = birth_month - 1;
    birth_struct.tm_mday = birth_date;
    birth = mktime(&birth_struct);

    int days_passed = (int)(difftime(now, birth) / (3600 * 24));

    return days_passed;
}
