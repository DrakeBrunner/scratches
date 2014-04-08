// SortStudent.h
/*
   Write between
   #define _SortStudent and #endif
*/
#ifndef _SortStudent
#define _SortStudent
/* ここに解答を書き加える */

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

#endif
