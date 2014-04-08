// 出力は降順（5,3,1,-1）として出力する。
// この問題では数値データの要素数を５と定義する。
//
// マージソートは指定したプログラムの形式で作成し，
// 1回のループごとに途中経過を出力しなさい．

// 1 配列を整列する関数babble_sort() を作成する。

// 2 二つのソートした配列をマージする関数merge()を作成する。
// 3 各配列を比較して大きいほうをdata3に格納し,
// 格納したデータがあるインデックスを1つ増やす
// 4 どちらかの配列が終端に到達した場合，
// data3に残りのデータを追加する
// 5 標準入力から数字を読み込み，配列に格納する。
// そして各配列をソートした結果と、
// それらをマージした結果を出力する。

#include <stdio.h>

#define N 5

// バブルソート
void bubble_sort(int data[], int n)
{

    printf("bubble_sort\n");
 //
    int i, j;   
    int temp;
    for(i = 0; i < n - 1; i++) {
        for(j = 1; j < (n - i); j++) {
            if(data[j - 1] < data[j]) { 
                temp = data[j - 1];
                data[j - 1] = data[j];
                data[j] = temp;
            }
        }
    }   

    //
}


// マージソート
void merge(int data1[], int n1, int data2[], int n2, int data3[])
{
    int index1, index2, index3, i;
    index1 = index2 = index3 = 0;

    printf("merge_sort\n");

    while(index1 < n1 || index2 < n2)
    {
//
        if (index1 == N) {
            for (i = 0; i < N - index2; i++)
                data3[index3 + i] = data2[index2 + i];
            break;
        }

        if (index2 == N) {
            for (i = 0; i < N - index1; i++)
                data3[index3 + i] = data1[index1 + i];
            break;
        }

        if (data1[index1] > data2[index2])
            data3[index3++] = data1[index1++];
        else
            data3[index3++] = data2[index2++];

//
        for(i = 0; i < (2 * N); i++) {
            printf("%d,", data3[i]);
        }
        putchar('\n');
    }
}


// main関数
int main(void)
{
    int i;
    /* int data1[N] = {56, 25, 4, 3, -1}; */
    /* int data2[N] = {21, 12, 9, -12, -23}; */
    int data1[N] = {5, 4, 3, 2, 0};
    int data2[N] = {5, 4, 3, 2, 1};
    /* int data1[N] = {40000, 5000, 100, -232, -400}; */
    /* int data2[N] = {100000, -67, -400, -2345, -8953}; */
    /* int data1[N]; */
    /* int data2[N]; */
    int data3[N * 2];

    for(i = 0; i < N; i++) {
        /* scanf("%d", &data1[i]); */
    }

    for(i = 0; i < N; i++) {
        /* scanf("%d", &data2[i]); */
    }

    for(i = 0; i < (N * 2); i++) {
        data3[i] = 0;
    }


    bubble_sort(data1, N);
    printf("bubble sort : result\n");

    for(i = 0; i < N ; i++) {
        printf("%d,", data1[i]);
    }
    putchar('\n');

     
    bubble_sort(data2, N);
    printf("bubble sort : result\n");

    for(i = 0; i < N; i++) {
        printf("%d,", data2[i]);
    }
    putchar('\n');
                 
                            
    merge(data1, N, data2, N, data3);
    printf("merge : result\n");

    for(i = 0; i < (2 * N); i++) {
        printf("%d,", data3[i]);
    }
    putchar('\n');
                            

    return(0);
}
