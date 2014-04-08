/*
 *
 * 数値解析 演習(2) ガウス消去法・ガウスザイデル法の雛形プログラム
 *
 * このソースファイルには，
 *   - 係数行列と右辺ベクトルを読み込む関数,および
 *   - それらの値を出力する関数
 * が定義されています．
 *
 * 読み込んだ各パラメータは，拡大行列として
 * 二次元の大域変数 "a" に，次数は "n" に格納されます．
 *
 * まずは，このままコンパイル・実行してみてください。
 * 
 *   % gcc -o sample gauss_template.c
 *   % ./sample
 *   
 * 行列を読み込んでそのまま出力するプログラムが動きます。
 * これで動作を確認してから演習にのぞんでください。
 *
 * 詳細はプログラム中のコメントをよく読んでください．
 *
 * なお，レポートに提出するのは main 関数内のみでかまいません．
 * 入出力関数を変更したり自作のものを使った場合は，それらの変更点も含めて
 * 提出してください．
 *
 */


/**********************************************************************/
/* ヘッダのインクルード，固定値の設定 */
#include <stdio.h>

#define MAXN 20			/* n の最大値 */
#define EPSILON 0.000001

/**********************************************************************/
/* 大域変数 … 読み込んだ行列の情報はここにセットされる */

/*
 * n 元
 *
 */
int n;

/*
 * 係数行列 A と右辺ベクトル b を合わせた拡大行列 [A b] を格納する．
 * A は a[1][1] ... a[n][n] に，
 * b は a[1][n+1] ... a[n][n+1] に格納される．
 *
 */
double a[MAXN+1][MAXN+2];

/**********************************************************************/
/*
 * 拡大行列 m[][] の内容を標準出力に出力する
 *
 * 引数：なし
 * 返値：なし
 * 関連する大域変数：a[][], n
 *
 */
void
print_matrix()
{
  int j, k;
  
  printf("/%*s\\\n", (n + 1) * 18 + 1, " ");
  for(j=1;j<=n;j++) {
    printf("|");
    for(k=1;k<=n;k++) {
      printf("% 13.10e ", a[j][k]);
    }
    printf(" ");
    printf("% 13.10e", a[j][n+1]);
    printf(" |\n");
  }
  printf("\\%*s/\n", (n + 1) * 18 + 1, " ");
} 

/**********************************************************************/
/*
 * 連立一次方程式の係数を標準入力から読み込んで，大域変数 n と a[][] に
 * 格納する．
 * A は a[1][1] ... a[n][n] に，
 * b は a[1][n+1] ... a[n][n+1] にそれぞれ格納される．
 *
 * 引数：なし
 * 返値：なし
 * 関連する大域変数：a[][], n
 *
 */
int
read_matrix()
{
  int j,k;

  /* n の入力 */
  printf("n="); scanf("%d", &n);
  if (n > MAXN) {
    printf("n > %d!!\n", MAXN);
    return -1;
  } else if (n < 1) {
    printf("n < 1!!\n");
    return -1;
  }
  
  /* 係数と右辺値を１行ずつ入力 */
  for(j=1;j<=n;j++) {
    /* aの入力 */
    for(k=1;k<=n;k++) {
      printf("a_%d%d=", j, k); scanf("%lf", &(a[j][k]));
    }
    /* bの入力 */
    printf("b_%d =", j); scanf("%lf", &(a[j][n+1]));
  }
  /* ここで入力終了 */

  return 0;
}

/**
 * A function that simply swaps the 2 given number.
 */
void swap(double* a, double* b) {
    double tmp = *a;
    *a = *b;
    *b = tmp;
}

/**
 * Given 2 integers indicating the row and column of the pivot,
 * this function swaps that row with the next row using the swap() function.
 * This function swaps with the row that has the maximum difference.
 */
void swapRows(int j, int k) {
    if (j > n - 1 || k > n + 1)
        return;

    // First, find where the maximum difference occurs.
    int i;
    double maxDiffSoFar = 0;
    int indexOfMaxDiff = j;
    for (i = j; i <= n; i++) {
        double diff = a[i][k] - a[j][k] > 0 ? a[i][k] - a[j][k] : -(a[i][k] - a[j][k]);
        if (diff > maxDiffSoFar) {
            maxDiffSoFar = diff;
            indexOfMaxDiff = i;
        }
    }

    printf("swapped %d with %d\n", j, indexOfMaxDiff);
    for (i = k; i <= n + 1; i++)
        swap(&a[j][i], &a[indexOfMaxDiff][i]);
}

int main() {
    /************************************************************/
    /*                                                          */
    /*  ここに課題のプログラムを書く．                          */
    /*                                                          */
    /*  関数 read_matrix() を呼ぶことで，標準入力から連立一次   */
    /*  方程式の次数および各係数を読み込む．その結果，          */
    /*  次数が大域変数 n に，                                   */
    /*  係数行列 A と右辺ベクトル b を合わせた拡大行列 [A b] が */
    /*  帯域変数 a[][] に，それぞれ格納される．                 */
    /*  なお，a[][]の内部は                                     */
    /*      A → a[1][1] ... a[n][n] ，                         */
    /*      b → a[1][n+1] ... a[n][n+1]                        */
    /*  のようになっている．                                    */
    /*                                                          */
    /*  関数 print_matrix() は  a[][] の内容を標準出力に        */
    /*  出力する．アルゴリズムのデバッグに活用すべし．          */
    /*                                                          */
    /************************************************************/


    /* 以下はサンプルプログラムで，行列を読み込んでそのまま出力する．*/

    /* 標準入力を読み込んで，係数行列を a[][] に，次数を n に格納する */
    if (read_matrix() != 0) {	/* 返り値が 0 以外であればエラー */
        fprintf(stderr, "input error!\n");
        return -1;
    }

    /* 現在の係数行列 a[][] の内容を出力する */
    print_matrix();

    /* ここにガウス消去法などの課題のプログラムを書く */


    // Forward elimination
    int j, k;
    // Flag that shows whether there is an unique solution or not. 0 if not, 1
    // otherwise. Default is 0 and it will be changed when not ALL j >= k is 0.
    int uniqueSolution = 0;
    for (k = 1; k <= n; k++) {
        for (j = k + 1; j <= n; j++) {

            // (a)
            if (j >= k && a[j][k] != 0)
                uniqueSolution = 1;

            // Swap rows if a[k][k] is zero
            // or if the difference is less than a particular value
            double diff = a[k][k] - a[j][k] > 0 ? a[k][k] - a[j][k] : -(a[k][k] - a[j][k]);
            /* Test data that shows this edit is valid
               3
               0.000000000000000001 1 2 4
               0.000000000000000001 2 2 7
               1 2 1 3
            */
            if ((a[k][k] >= 0 && a[k][k] < EPSILON) || (diff > 0 && diff < EPSILON))
                swapRows(k, k);

            // (b)
            // i
            double mjk = a[j][k] / a[k][k];
//printf("k: %d\n", k);
//printf("mjk = %f / %f = %f\n", a[j][k], a[k][k], mjk);

            // ii
            int p;
            for (p = k; p <= n + 1; p++)
                a[j][p] -= mjk * a[k][p];
        }
    }

    if (uniqueSolution == 0 || a[n][n] == 0) {
        printf("No Unique Solution\n");
        return 0;
    }

    // Checking if the forward elimination went good
    printf("After forward elimination\n");
    print_matrix();

    // Now, on to the backward substitution
    double x[n + 1];
    x[n] = a[n][n + 1] / a[n][n];
    int i;
    // Since we've already done n 2 lines before, we start with n - 1
    for (i = n - 1; i >= 1; i--) {
        double sum = 0;
        for (j = i + 1; j <= n; j++)
            sum += a[i][j] * x[j];

        x[i] = (a[i][n + 1] - sum) / a[i][i];
    }

    // Finally, print the result
    for (j = 1; j < n + 1; j++)
        printf("%f ", x[j]);

    /* プログラムの終了 */
    return 0;
}
