#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 20

void quick(int *, int, int);
void quicker(int *, int, int);
int pivot(int a[], int left, int right, int *pos);
void swap(int *x, int *y);
int partition(int a[], int left, int right, int p);

void printarray(char *s, int *a, int n);
int comparearray(int *a, int *b, int n);
int comp(const void *a, const void *b);
void debug(int a[], int left, int right, int p, int center);

// ピボットの値を求める関数
int pivot(int a[], int left, int right, int *pos) {
    // (* ここに解答を書き加える *)
    // (* ここに解答を書き加える *)
    int mid = (int)((left + right) / 2);
    int index;
    if (a[left] > a[right])
        index = a[mid] > a[left] ? left : a[right] > a[mid] ? right : mid;
    else
        index = a[mid] > a[right] ? right : a[left] > a[mid] ? left : mid;

    *pos = index;
    return a[index];
}

// xとyを入れ替える関数
void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
}


// 配列aのleftからrightまでをピボットpで分割する関数
int partition(int a[], int left, int right, int pivotIndex) {
    // (* ここに解答を書き加える *)
    swap(&a[pivotIndex], &a[left]);
    pivotIndex = left;

    int i, j;
    i = left + 1;
    j = right;

    while (1) {

        while (a[i] < a[pivotIndex])
            i++;

        while (a[j] > a[pivotIndex])
            j--;

        if (i >= j)
            break;

        swap(&a[i], &a[j]);
        i++;
        j--;
    }

    swap(&a[left], &a[j]);

    return j;
}

// Normal quick sort
void quick(int a[], int left, int right) {

    if (right - left < 1)
        return;

    int pivotIndex;
    int p = pivot(a, left, right, &pivotIndex);
    int center = partition(a, left, right, pivotIndex);

    //  if (left - right <= 1)
    //    return;

    quick(a, left, center - 1);
    quick(a, center + 1, right);
    return;
}

// 配列aのleftからrightまでをクィックソートする関数
// quick sort WITH bubble sort
void quicker(int a[], int left, int right) {
    // Bubble sort when the size of array is or smaller than 4
    if (N <= 4) {
        int i, j;
        for (i = 0; i < N - 1; i++)
            for (j = 1; j < N - i; j++)
                if (a[j - 1] > a[j])
                    swap(&a[j - 1], &a[j]);
        return;
    }

    if (right - left < 1)
        return;

    int pivotIndex;
    int p = pivot(a, left, right, &pivotIndex);
    int center = partition(a, left, right, pivotIndex);

    debug(a, left, right, p, center);

    quicker(a, left, center - 1);
    quicker(a, center + 1, right);
    return;
}

// 文字列sと長さnの配列aを表示する関数
void printarray(char *s, int *a, int n) {
    int i;
    printf("%s", s);
    for (i = 0; i < n; i++)
        printf("%4d", a[i]);
    printf("\n");
}

// 長さnの配列aとbを比較する関数
int comparearray(int *a, int *b, int n) {
    int i;
    for (i = 0; i < n; i++)
        if (a[i] != b[i])
            return a[i] - b[i];
    return 0;
}

// qsort用の関数
int comp(const void *a, const void *b) {
    return *(int*)a - *(int*)b;
}

// デバッグ用の関数
void debug(int a[], int left, int right, int p, int center) {
    int i = 0;
    printf("(%d, %d) pivot: %d, center: %d\n", left, right, p, center);
    for (i = 0; i < N; i++)
        printf("%4d", a[i]);
    printf("\n");
}

int  main(void) {
    // Set to 1 when debugging
    int debug = 0;
    int *a = NULL, *b = NULL, *c = NULL, *d = NULL;
    int i;
    int seed;

    a = calloc(N, sizeof(int));
    b = calloc(N, sizeof(int));
    c = calloc(N, sizeof(int));
    d = calloc(N, sizeof(int));
    if (a == NULL || b == NULL || c == NULL || d == NULL) {
        if (a == NULL) free(a);
        if (b == NULL) free(b);
        if (c == NULL) free(c);
        if (d == NULL) free(d);
        return -1;
    }

    printf("Input integer: ");
    scanf("%d", &seed);
    srand(seed);

    clock_t start, end;
    for (i = 0; i < N; i++)
        a[i] = b[i] = c[i] = d[i] = rand() % 1000;

    // ソート前の配列を表示したい場合は、次の1行のコメントを外す
    //printarray("orig : ", a, N);

    start = clock();
    quicker(d, 0, N - 1);
    end = clock();
    // Print result for the improved version of quick sort
    printarray("result: ", d, N);
    if (debug)
        printf("%lf sec, ", (double)(end - start)/CLOCKS_PER_SEC);

    if (debug) {
        start = clock();
        quick(a, 0, N - 1);
        end = clock();
        printf("%lf sec, ", (double)(end - start)/CLOCKS_PER_SEC);

        start = clock();
        qsort(b, N, sizeof(int), comp);
        end = clock();
        printf("%lf sec\n", (double)(end - start)/CLOCKS_PER_SEC);
    }

    free(a);
    free(b);
    free(c);
    free(d);

    return 0;
}
