#include <stdio.h>
#include <string.h>

/* Personal Data */
typedef struct {
    char name[100];
    double midterm;
    double final;
    double average;
} member;


/* Swap struct */
// START
void sort(member students[], int sizeof_array) {
    int i, j;
    member tmp;

    for (i = 0; i < sizeof_array - 1; i++) {
        for (j = 1; j < sizeof_array - i; j++) {
            if (students[j - 1].average < students[j].average) {
                tmp = students[j - 1];
                students[j - 1] = students[j];
                students[j] = tmp;
// DEBUG
// printf("Swapped! %d <=> %d\n", j - 1, j);
            }
        }
    }
}

int main(void) {
    FILE *fp;
    member ma[10];

    int i = 0;

    char filename[60];

    printf("File name: ");
    scanf("%s", filename);

    // Open file
    if ( (fp = fopen(filename, "r")) == NULL ) {
        printf("Could not open file!\n");
        return 1;
    }
    else {
        int j;
        int k;
        while (fscanf(fp,
                    "%s%lf%lf", ma[i].name, &(ma[i].midterm), &(ma[i].final)) == 3) {
            printf("%-10s %5.1f %5.1f\n",
                    ma[i].name, ma[i].midterm, ma[i].final);
            i++;
        }

        // Calculate average and sort in descending order
        int sizeof_array = sizeof(ma) / sizeof(member);
// DEBUG
// printf("Size of array: %d\n", sizeof_array);
        for (i = 0; i < sizeof_array; i++) {
            ma[i].average = (ma[i].midterm + ma[i].final) / 2;
// DEBUG
// printf("%s: %5.1f\n", ma[i].name, ma[i].average);
        }
        sort(ma, sizeof_array);
        j = sizeof_array;

        // Display sort result
        printf("------------Sort Result------------\n");
        for (i = 0; i < j; i++)
            printf("%-10s %5.1f %5.1f %5.1f\n",
                    ma[i].name, ma[i].midterm, ma[i].final, ma[i].average);
    }

    return 0;
}
