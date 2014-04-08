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
  
  /* 3. 分散共分散行列の計算 */
  
  /* 4. jacobi()の計算結果を格納するための領域の確保 */
  
  /* 5. jacobi()を呼び出す */
  
  /* 6. 得られた全ての固有ベクトルと平均ベクトルから各主成分の式を求めて出力 */
  
  /* 7. 各固有値から，各主成分の寄与率を求めて出力 */
  
  /* 8. データについてそれぞれの主成分での主成分得点を求め，出力 */

  return 0;
}

/* END OF FILE */
