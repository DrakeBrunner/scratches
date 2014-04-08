#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 20

void quick(int *, int, int);
int pivot(int a[], int left, int right);
void swap(int *x, int *y);
int partition(int a[], int left, int right, int p);

void printarray(char *s, int *a, int n);
int comparearray(int *a, int *b, int n);
int comp(const void *a, const void *b);
void debug(int a[], int left, int right, int p, int center);


// ピボットの値を求める関数
int pivot(int a[], int left, int right) {
    // (* ここに解答を書き加える *)
    return a[left];
}

// xとyを入れ替える関数
void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
}


// 配列aのleftからrightまでをピボットpで分割する関数
int partition(int a[], int left, int right, int p) {
    // (* ここに解答を書き加える *)
    int i, j;
    i = left + 1;
    j = right;

    while (1) {

        while (a[i] < p)
            i++;

        while (a[j] > p)
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


// 配列aのleftからrightまでをクィックソートする関数
void quick(int a[], int left, int right) {

    if (right - left < 1)
        return;

    int p = pivot(a, left, right);
    int center = partition(a, left, right, p);

    //  if (left - right <= 1)
    //    return;

    quick(a, left, center - 1);
    quick(a, center + 1, right);
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
    int a[N], b[N], c[N];
    int i;
    int seed;
    int count;
    int iteration = 10000;

   /*
    for (i = 0; i < N; i++) {
        printf("Input a number: ");
        scanf("%d", &a[i]);
    }
  */

    printf("Input integer: ");
    scanf("%d", &seed);
    srand(seed);

    for (count = 0; count < iteration; count++) {
    // printf(" %4d: ", count);

        clock_t start, end;
        for (i = 0; i < N; i++)
            a[i] = b[i] = c[i] = rand() % 1000;

        // ソート前の配列を表示したい場合は、次の1行のコメントを外す
        //printarray("orig : ", a, N);

        start = clock();
        quick(a, 0, N - 1);
        end = clock();
        // 結果を表示したい場合は、次の2行のコメントを外す
        // printf("%lf sec, ", (double)(end - start)/CLOCKS_PER_SEC);    // printarray("result: ", a, N);

        qsort(b, N, sizeof(int), comp);
        int cmp = comparearray(a, b, N);
        if (cmp != 0) {
            printf("-- Error --\n");
            printarray("orig : ", c, N);
            printarray("yours: ", a, N);
            printarray("qsort: ", b, N);
            break;
        }
    }

    if (count == iteration)
        printf("-- Conguraturations!! --\n");

    return 0;
}
