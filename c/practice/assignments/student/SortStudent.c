// SortStudent.c
#include <stdio.h>
#include <string.h>
#include "SortStudent.h"

void sort_by_height(student data[], int n) {
    student tmp;

    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = 1; j < n - i; j++) {
            // If [j - 1] is shorter than [j]
            if (data[j - 1].height < data[j].height) {
                tmp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = tmp;
            }
        }
    }
}

void sort_by_weight(student data[], int n) {
    student tmp;

    int i, j, k;
    for (i = 0; i < n - 1; i++) {
        for (j = 1; j < n - i; j++) {
            // If [j - 1] is heavier than [j]
            if (data[j - 1].weight > data[j].weight) {
                tmp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = tmp;
            }
        }
    }
}

void sort_by_name(student data[], int n) {
    student tmp;

    int length_of_shorter_name = 0;
    int first, second;
    int i, j, k;
    for (i = 0; i < n - 1; i++) {
        for (j = 1; j < n - i; j++) {
            // Get the length of the shorter name
            first = 0;
            second = 0;
            while (data[j - 1].name[first])
                first++;
            while (data[j].name[second])
                second++;

            // Compare the length of first and second name
            if (first < second)
                length_of_shorter_name = first;
            else
                length_of_shorter_name = second;

// printf("compare %s : %s size of shorter name %d\n", data[j - 1].name, data[j].name, length_of_shorter_name);

            // Sort by alphabetical order by comparing each letter
            for (k = 0; k < length_of_shorter_name - 1; k++) {
//printf("%d===> %c : %c\n", k, data[j - 1].name[k], data[j].name[k]);
            // Redo loop if it's the same letter
                if (data[j - 1].name[k] == data[j].name[k])
                    continue;

                // Reverse order if it's wrong
            else if (data[j - 1].name[k] > data[j].name[k]) {
//printf("reversing\n");
                    tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                    // Compare with the next person
                    break;
                }

                // When it's the right order
                else
//printf("okay, next\n");
                    break;
            }
        }
    }
}
