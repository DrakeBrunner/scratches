// 課題2
// 12-6-12

// main07-2.c       Function main (this file)
// SortStudent.h    Prototypes of struct and sort function
// SortStudent.c    Sort function

// grep tag: sort student height weight name makefile

#include <stdio.h>
#include <string.h>

/* ここに解答を書き加える */
#include "SortStudent.h"

#define NUMBER 5

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
