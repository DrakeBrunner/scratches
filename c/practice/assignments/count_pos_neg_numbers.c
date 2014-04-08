// 課題4
// grep tag: calloc int sum

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int *data;
    int *startPos;
    int num;
    int i;
    int sum_p;      // Sum of positive numbers
    int sum_n;      // Sum of negative numbers
    int count_p;    // Number of positive numbers
    int count_n;    // Number of negative numbers

    printf("Enter numbers of input: ");
    scanf("%d", &num);

    /* ここに解答を書き加える */
    if ( (data = calloc(num, sizeof(int))) == NULL ) {
        printf("Allocation Failed\n");
        return -1;
    }


    startPos = data;

    /* Input Data */
    for (i = 0; i < num; i++) {
        printf("\nInput data #%i > ", i + 1);
        scanf("%d", data);
        data++;
    }

    /* Initialize Variables */
    data = startPos;
    sum_p = 0;
    count_p = 0;
    sum_n = 0;
    count_n = 0;

    /* Output Data */
    for (i = 0; i < num; i++) {
        if (*data > 0) {
            sum_p = sum_p + *data;
            count_p++;
        }
        else if (*data < 0) {
            sum_n = sum_n + *data;
            count_n++;
        }
        data++;
    }

    printf("Positive Numbers:\n\tCount: %d\n\tSum: %d\n", count_p, sum_p);
    printf("Negative Numbers:\n\tCount: %d\n\tSum: %d\n", count_n, sum_n);

    /* ここに解答を書き加える */
    data = startPos;
    free(data);
    return 0;

    return 1;
}
