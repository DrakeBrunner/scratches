#include <stdio.h>
#include <time.h>

int main(int argc, char const* argv[]) {
    srand(time(NULL));

    int NUM_PAIRS = 5;
    int NUM_TRIALS = 1000000;

    int match_cnt = 0;
    int total_cnt = 0;

    /* Even is left, odd is right */
    int first_glove_ID, second_glove_ID;

    int i;
    for (i = 0; i < NUM_TRIALS; i++) {
        first_glove_ID = (int)(rand() % (2 * NUM_PAIRS));

        int no_dup = 1;
        while (no_dup) {
            second_glove_ID = (int)(rand() % (2 * NUM_PAIRS));

            if (first_glove_ID != second_glove_ID)
                no_dup = 0;
        }

        /* One is right, and the other is left */
        if (first_glove_ID % 2 != second_glove_ID % 2)
            match_cnt++;

        total_cnt++;

        /* printf("F: %d\nS: %d\n---\n", first_glove_ID, second_glove_ID); */
    }

    printf("MATCH/TOTAL=%d/%d\n", match_cnt, total_cnt);
    printf("%f%%\n", 100.0 * match_cnt / total_cnt);
    return 0;
}
