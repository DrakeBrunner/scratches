/**
 * @file   pca.c
 * 
 * @brief  主成分分析プログラムの雛型
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "jacobi.h"
#include "rddata.h"

/** 
 * メイン関数
 * 
 * @param argc [in] コマンド引数の数
 * @param argv [in] コマンド引数
 * 
 * @return エラー時 -1，正常終了時 0 
 */
int main(int argc, char *argv[])
{
    char *filename;
    double **data;
    int n, p;
    int i, j;

    /* コマンド引数無しで起動された場合，使い方を表示して終了する */
    if (argc <= 1) {
        printf("usage: %s datafile\n", argv[0]);
        return -1;
    }

    /* 入力データファイル名は第1コマンド引数とする */
    filename = argv[1];

    /* 1. データの読み込み */
    /* ファイル内のデータを配列 data に読み込む．*/
    /* n にフィールド数（＝データの次元数），p にデータ数が格納される */
    /* data のメモリ領域は rddata() 内で割り付けられる */
    data = rddata(filename, &n, &p);

    /* 読み込んだデータをそのまま出力 */
    for(i=0;i<p;i++) {
        printf("%d:", i);
        for(j=0;j<n;j++) {
            printf(" %f", data[i][j]);
        }
        printf("\n");
    }

    /* 2. 平均ベクトルの計算 */

    /*
     * References:
     * http://d.hatena.ne.jp/Zellij/20130510/p1
     * http://www.itl.nist.gov/div898/handbook/pmc/section5/pmc541.htm
     */
    double *x = malloc_vector(n);
    for (i = 0; i < n; i++) {
        for (j = 0; j < p; j++)
            x[i] += data[j][i];
        x[i] /= p;
    }

    // DEBUG
    /* printf("Mean vector\n"); */
    /* for (i = 0; i < n; i++) */
    /*     printf("%f ", x[i]); */
    /* printf("\n"); */

    /* 3. 分散共分散行列の計算 */
    double **S = malloc_matrix(n);
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            double result = 0;
            int k;
            for (k = 0; k < p; k++)
                result += (data[k][i] - x[i]) * (data[k][j] - x[j]);

            S[i][j] = result / p;
        }
    }

    // DEBUG
    /* printf("Printing out the Variance-Covariance matrix\n"); */
    /* for (i = 0; i < n; i ++) { */
    /*     for (j = 0; j < n; j++) */
    /*         printf("%f ", S[i][j]); */
    /*     printf("\n"); */
    /* } */

    /* 4. jacobi()の計算結果を格納するための領域の確保 */
    double *eigenvalue = malloc_vector(n);
    double **eigenvector = malloc_matrix(n);

    /* 5. jacobi()を呼び出す */
    jacobi(S, n, 0.00001, eigenvalue, eigenvector, 1000);
    // DEBUG
    /* output_vector(eigenvalue, n); */
    /* output_matrix(eigenvector, n); */

    /* 6. 得られた全ての固有ベクトルと平均ベクトルから各主成分の式を求めて出力 */
    for (i = 0; i < p; i++) {
        printf("Data set %d:\n", i + 1);
        for (j = 0; j < n; j++) {
            int k;
            //double z = 0;
            printf("  z%d =", j + 1);
            for (k = 0; k < n; k++) {
                //z += eigenvector[j][k] * (data[i][k] - x[k]);
                if (k != 0 && eigenvector[j][k] >= 0)
                    printf(" +");
                printf(" %f(%.2f - %.2f)", eigenvector[j][k], data[i][k], x[k]);
            }
            //printf(" = %f\n", z);
            printf("\n");
        }
        printf("\n");
    }

    /* 7. 各固有値から，各主成分の寄与率を求めて出力 */

    double subTotal = 0;
    for (i = 0; i < n; i++)
        subTotal += S[i][i];

    int k;
    for (k = 0; k < n; k++) {
        double contribution = S[k][k] / subTotal;
        printf("Contribution for component #%d: %f%%\n", k + 1, contribution * 100);
    }
    printf("\n");

    /* 8. データについてそれぞれの主成分での主成分得点を求め，出力 */
    for (i = 0; i < p; i++) {
        printf("Data set %d:\n", i + 1);
        for (j = 0; j < n; j++) {
            int k;
            double z = 0;
            for (k = 0; k < n; k++)
                z += eigenvector[j][k] * (data[i][k] - x[k]);
            printf("  z%d = %f\n", j + 1, z);
        }
        printf("\n");
    }

    free_vector(x);
    free_matrix(S);
    return 0;
}

/* END OF FILE */
