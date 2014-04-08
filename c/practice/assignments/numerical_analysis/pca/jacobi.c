/**
 * @file   jacobi.c
 * 
 * @brief  ヤコビ法による対称行列の固有値計算（ソース）
 *
 * ヤコビ法を用いた対称行列の固有値を計算する関数，および
 * 行列やベクトルのための汎用関数群が定義されています．
 * 
 * 行列やベクトルの添字は 0 から始まることに注意してください．
 * 例：n x n の正方行列 m は， m[0..(n-1)][0..(n-1)] となります．
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/** 
 * 正方行列の内容をテキスト出力する．
 * 
 * @param m [in] n x n の正方行列へのポインタ
 * @param n [in] @a m の次数
 */
void
output_matrix(double **m, int n)
{
  int j, k;
  
  for(j=0;j<n;j++) {
    printf("|");
    for(k=0;k<n;k++) {
      printf("% 12.8f ", m[j][k]);
    }
    printf(" |\n");
  }
}

/** 
 * ベクトルの内容をテキスト出力する．
 * 
 * @param v [in] n 次のベクトルへのポインタ
 * @param n [in] @a v の次数
 */
void
output_vector(double *v, int n)
{
  int j;
  
  printf("(");
  for(j=0;j<n;j++) {
    printf("% 12.8f ", v[j]);
  }
  printf(")\n");
}

/** 
 * 指定された次数の正方行列のメモリ領域を新たに確保する．
 * 
 * @param n [in] 行列の次数
 * 
 * @return 新たに割り付けられた n x n 行列のメモリ領域へのポインタ
 */
double **
malloc_matrix(int n)
{
  double **m;
  double *mtmp;
  int i, j;

  if ((mtmp = (double *)malloc(sizeof(double) * n * n)) == NULL) {
    return NULL;
  }
  if ((m = (double **)malloc(sizeof(double *) * n)) == NULL) {
    free(mtmp);
    return(NULL);
  }
  for (i=0; i<n; i++) {
    m[i] = &(mtmp[i*n]);
  }
  return m;
}

/** 
 * 行列のメモリ領域を解放する
 * 
 * @param m [in] 行列へのポインタ
 */
void
free_matrix(double **m)
{
  int i;
  free(m[0]);
  free(m);
}

/**
 * 指定された次数のベクトルを新たに確保する．
 * 
 * @param n [in] ベクトルの次数
 * 
 * @return 新たに割り付けられた n 次のベクトルのメモリ領域へのポインタ
 */
double *
malloc_vector(int n)
{
  return ((double *)malloc(sizeof(double) * n));
}

/**
 * ベクトルのメモリ領域を解放
 *
 * @param v [in] ベクトルへのポインタ
 */
void
free_vector(double *v)
{
  int i;
  free(v);
}


/**********************************************************************/
/** 
 * @brief  ヤコビ法による固有値・固有ベクトル計算
 *
 * n x n の対称行列の固有値と固有ベクトルを，ヤコビ法を用いてすべて求める．
 * itemax 回以上手順を繰返ししても収束判定値 eps 内に収まらない場合は，
 * 収束失敗としてエラー終了する．
 *
 * 得られた固有値は配列 @a e_val, 固有ベクトルは二次元配列 @a e_vec に
 * 格納される．これらは内部で malloc されないので，呼び出し前に
 * 最低 @a n 要素分領域確保してある必要がある．固有値は大きい順に格納される．
 * 
 * @param m [in] 固有値を求める対称行列 (n x n)
 * @param n [in] @a m の次数
 * @param eps [in] 収束判定値
 * @param e_val [out] 得られた固有値列（出力）
 * @param e_vec [out] 得られた固有ベクトル列（出力）
 * @param itemax [in] 最大繰返し回数
 * @return 結果が得られたときまでの繰返し回数を返す．入力エラー
 * 時（@a m が対称行列でないなど）は -1， @a itemax 回繰り返しても
 * 収束しなかった場合は -2 を返す．
 */
int
jacobi(double **m, int n, double eps, double *e_val, double **e_vec, int itemax)
{
  int i, j, k;
  int count;
  int ret;
  double **a;
  double max_e;
  int r, c;
  double a1, a2, a3;
  double co, si;
  double w1, w2;
  double t1, ta;
  double tmp;
  
  /* m が対称行列かどうかチェック */
  for(i=0;i<n;i++) {
    for(j=i+1;j<n;j++) {
      if (m[i][j] != m[j][i]) {
	printf("matrix is not synmetric!\n");
	return -1;
      }
    }
  }
  /* m を 作業領域 a にコピー */
  a = malloc_matrix(n);
  for(i=0;i<n;i++) {
    for(j=0;j<n;j++) {
      a[i][j] = m[i][j];
    }
  }
  /* 固有ベクトルの格納行列 p (e_vec) を単位行列に初期化 */
  for(i=0;i<n;i++) {
    for(j=0;j<n;j++) {
      e_vec[i][j] = (i == j) ? 1.0 : 0.0;
    }
  }
  /* カウンタリセット */
  count = 0;
  
  /* 収束判定値までループ */
  while(1) {
    /* a[][] 内で絶対値最大の要素を見つける（上三角内で） */
    max_e = 0.0;
    for(i=0;i<n;i++) {
      for(j=i+1;j<n;j++) {
	if (max_e < fabs(a[i][j])) {
	  max_e = fabs(a[i][j]);
	  r = i;
	  c = j;
	}
      }
    }
    /* 最大要素の値が eps 以下なら収束として終了 */
    if (max_e <= eps) {
      ret = count;
      break;
    }
    /* 繰返し回数が itemax 以上になったらあきらめて終了 */
    if (count >= itemax) {
      printf("loop reached %d!\n", itemax);
      ret = -2;
      break;
    }

    /* a[r][c] を 0 にするような回転行列 P_k を計算する */
    /* 
       p[r][r] = co   p[r][c] = si
       p[c][r] = -si  p[c][c] = co
    */
    a1 = a[r][r];
    a2 = a[c][c];
    a3 = a[r][c];
    
    t1 = fabs(a1 - a2);
    ta = 2.0 * a3 / (t1 + sqrt(t1 * t1 + 4.0 * a3 * a3));
    co = sqrt(1.0 / (ta * ta + 1.0));
    si = ta * co;
    if (a1 < a2) si = -si;

    /* a[][] および p[][] (e_vec[]) に P_k をかけて更新  */
    for(i=0;i<n;i++) {
      w1 = e_vec[i][r];
      w2 = e_vec[i][c];
      e_vec[i][r] = w1 * co + w2 * si;
      e_vec[i][c] = -w1 * si + w2 * co;
      if (i == r || i == c) continue;
      w1 = a[i][r];
      w2 = a[i][c];
      a[i][r] = w1 * co + w2 * si;
      a[i][c] = -w1 * si + w2 * co;
      a[r][i] = a[i][r];
      a[c][i] = a[i][c];
    }
    a[r][r] = a1 * co * co + a2 * si * si + 2.0 * a3 * co * si;
    a[c][c] = a1 + a2 - a[r][r];
    a[r][c] = 0.0;
    a[c][r] = 0.0;
    
    /* カウンタをインクリメント */
    count++;
  }
  
  /* 固有値は a の対角成分として得られるので，出力用バッファにコピー */
  for(i=0;i<n;i++) {
    e_val[i] = a[i][i];
  }
  /* 固有ベクトルは e_vec[次元][固有値ID] の形で格納されているので，
     転置して e_vec[固有値ID][次元] にしておく */
  for(i=0;i<n;i++) {
    for(j=i+1;j<n;j++) {
      tmp = e_vec[i][j];
      e_vec[i][j] = e_vec[j][i];
      e_vec[j][i] = tmp;
    }
  }
  /* 固有値の大きい順に固有値列・固有ベクトル列をソートする（バブルソート） */
  for(i=0;i<n;i++) {
    for(j=n-2;j>=i;j--) {
      if (e_val[j] < e_val[j+1]) {
	tmp = e_val[j];
	e_val[j] = e_val[j+1];
	e_val[j+1] = tmp;
	for(k=0;k<n;k++) {
	  tmp = e_vec[j][k];
	  e_vec[j][k] = e_vec[j+1][k];
	  e_vec[j+1][k] = tmp;
	}
      }
    }
  }

  /* 作業領域を解放 */
  free_matrix(a);

  return (ret);
}

/* END OF FILE */
