#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 20

void quick(int *, int, int);
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
    int i, j;
    int pivotValue = a[pivotIndex];
    i = left;
    j = right;

    // DEBUG
    int cnt;

    while (1) {
        /* printf("piv: a[%d] = (%d)   %d : %d    i, j = %d %d\n", pivotIndex, a[pivotIndex], left, right, i, j); */
        for (cnt = 0; cnt < N; cnt++)
            printf("%4d", a[cnt]);
        printf("\n");

        while (a[i] < pivotValue)
            i++;
        while (a[j] > pivotValue)
            j--;

        /* if (i >= j) */
        /*     break; */
        if (i >= j) {
            while (pivotIndex < i) {
                swap(&a[pivotIndex], &a[pivotIndex + 1]);
                pivotIndex++;
printf("piv on the left of i,j pivotIndex = %d\n", pivotIndex);
for (cnt = 0; cnt < N; cnt++)
    printf("%4d", a[cnt]);
printf("\n");
            }
            while (pivotIndex > j) {
                swap(&a[pivotIndex], &a[pivotIndex - 1]);
                pivotIndex--;
printf("piv on the right of i,j pivotIndex = %d\n", pivotIndex);
for (cnt = 0; cnt < N; cnt++)
    printf("%4d", a[cnt]);
printf("\n");
            }
            return pivotIndex;
        }

        // Update pivotIndex and pivotValue
        if (i == pivotIndex) {
            pivotIndex = j;
            pivotValue = a[pivotIndex];
            /* quick(a, i + 1, j - 1); */
        }
        else if (j == pivotIndex) {
            pivotIndex = i;
            pivotValue = a[pivotIndex];
            /* quick(a, i + 1, j - 1); */
        }
        swap(&a[i], &a[j]);
        i++;
        j--;

    }

    /* swap(&a[pivotIndex], &a[j]); */
    /* printf("END OF ONE PARTITION\n"); */
    /* return pivotIndex; */
}


// 配列aのleftからrightまでをクィックソートする関数
void quick(int a[], int left, int right) {

    if (right - left < 1)
        return;

    int pivotIndex;
    int pivotValue = pivot(a, left, right, &pivotIndex);
    int center = partition(a, left, right, pivotIndex);

    //if (left - right <= 1)
    //  return;

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
