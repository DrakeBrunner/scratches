#include <stdio.h>
#include <string.h>

#define NUMBER 5

typedef struct {
    char    name[20];
    int     height;
    float   weight;
    long    schols;
} student;

// Prototypes
void sort_by_height(student data[], int n);
void sort_by_weight(student data[], int n);
void sort_by_name(student data[], int n);


int main(void) {
    int i;
    int cont = 1;
    student std[5];

    for (i = 0; i < 5; i++) {
        scanf("%s", std[i].name);
//        scanf("%d", &std[i].height);
//        scanf("%f", &std[i].weight);
//        scanf("%ld", &std[i].schols);
    }
    std[0].height = 182; 
    std[0].weight = 62; 
    std[0].schols = 62000; 
    std[1].height = 172; 
    std[1].weight = 72; 
    std[1].schols = 82000; 
    std[2].height = 186; 
    std[2].weight = 66; 
    std[2].schols = 68000; 
    std[3].height = 179; 
    std[3].weight = 71; 
    std[3].schols = 52000; 
    std[4].height = 192; 
    std[4].weight = 92; 
    std[4].schols = 92000; 

    while (cont) {
        printf("please select: sort by <1: height, 2:weight, 3:name, 0:quit>\n");
        scanf("%d", &cont);
        switch (cont) {
            case 1:
                printf("height\n");
                sort_by_height(std, NUMBER);
                break;
            case 2:
                printf("weight\n");
                sort_by_weight(std, NUMBER);
                break;
            case 3:
                printf("name\n");
                sort_by_name(std, NUMBER);
                break;
        }
        if (cont) {
            puts("-----------------------------------\n");
            for (i = 0; i < NUMBER; i++)
                printf("%-8s %6d%6.1f%7ld\n",
                        std[i].name, std[i].height, std[i].weight, std[i].schols);
            puts("-----------------------------------\n");
        }
    }
    return 0;
}


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
//
