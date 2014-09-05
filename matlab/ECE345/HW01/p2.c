#include <stdio.h>
int main(int argc, char const* argv[]) {
    srand(time(NULL));

    int NUM_TRIALS = 1000000;

    int success_cnt = 0;
    int total_cnt = 0;

    int i;
    for (i = 0; i < NUM_TRIALS; i++) {
        int j;
        for (j = 0; j < 4; j++) {
            int roll = (int)(rand() % 6) + 1;

            if (roll == 6) {
                success_cnt++;
                break;
            }
        }

        total_cnt++;
    }

    printf("SUCCESS/TOTAL=%d/%d\n", success_cnt, total_cnt);
    printf("%f%%\n", 100.0 * success_cnt / total_cnt);
    return 0;
}
