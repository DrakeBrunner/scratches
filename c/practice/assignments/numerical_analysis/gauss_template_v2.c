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
#define epsilon 0.000001
#define N 100

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
 * Given 2 integers indicating the rows,
 * this function swaps that row with the other row using the swap() function.
 */
void swapRows(int j, int k) {
    if (j > n || k > n)
        return;

    int i;
    for (i = 1; i <= n + 1; i++)
        swap(&a[j][i], &a[k][i]);
}

/**
 * Find the maximum absolute value (= difference) among the values
 */
double findMaxDelta(double values[], double prev_values[]) {
    double maxSoFar = 0;

    int i;
    for (i = 0; i < n; i++) {
        // Get the absolute value
        double diff = values[i] - prev_values[i] > 0
            ? values[i] - prev_values[i] : -(values[i] - prev_values[i]);
        if (diff > maxSoFar)
            maxSoFar = diff;
    }
    return maxSoFar;
}

/**
 * Simply copies the values in the array "prev_values" to the array "values"
 */
void copyValues(double values[], double prev_values[]) {
    int i;
    for (i = 0; i < n; i++)
        prev_values[i] = values[i];
}

/**
 * A function as simple as the previous --prints out the contents of the given
 * array with a length of n.
 */
void printValues(double values[]) {
    int i;
    for (i = 0; i < n; i++)
        printf("%f ", values[i]);
    printf("\n");
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

    // Instead of Gaussian elimination, we'll use gauss-seidel method this

    // These 2 variables are used to test every cases of swapping (like a
    // bubble sort)
    int rowToSwap;
    int rowToSwap2;
    int allCombinations;
    for (allCombinations = 0; allCombinations < n; allCombinations++) {
        for (rowToSwap = 1; rowToSwap < n; rowToSwap++) {
            for (rowToSwap2 = n; rowToSwap2 >= 1; rowToSwap2--) {

                double values[MAXN] = {0};
                double prev_values[MAXN] = {0};
                
                // Checking if the equations are solved for x_1, x_2 ...
                print_matrix();

                // Repeat this loop N times at maximum
                int i, j, k;
                int invalidDivision = 0;
                for (i = 0; i < N; i++) {

                    for (j = 1; j <= n; j++) {

                        if (a[j][j] == 0) {
                            invalidDivision = 1;
                            break;
                        }

                        // Initialize before starting
                        values[j - 1] = 0;

                        for (k = 1; k <= n; k++) {


                            double multi = values[k - 1];
                            if (k == j)
                                multi = 0;

                            // Since j starts from 1
                            // The sign will be flipped when moving to the right side
//printf("values[%d]: %f += %f * %f\n", j - 1, values[j - 1], -(a[j][k] / a[j][j]), multi);
                            values[j - 1] += -(a[j][k] / a[j][j]) * multi;
                        }

//printf("values[%d] = %f (%f += %f)\n", j - 1, values[j - 1] + a[j][n + 1] / a[j][j], values[j - 1], a[j][n + 1] / a[j][j]);
                        // Add the integer (= Matrix "b")
                        values[j - 1] += a[j][n + 1] / a[j][j];
                    }

                    if (invalidDivision == 1)
                        break;

                    // After the whole matrix has been processed, check if the result
                    // was accurate enough.
                    double d = findMaxDelta(values, prev_values);
printf("d: %f\n", d);

                    // Stop when it becomes accurate enough
                    if (d <= epsilon) {
                        printValues(values);
                        return 0;
                    }
                    // Now, make a backup of the current values (so that we can
                    // calculate the delta in the next loop
                    copyValues(values, prev_values);
                }

                // If the program comes out of this loop, it means that N trials were done
                // but the solutions didn't get accurate enough.
                swapRows(rowToSwap, rowToSwap2);
//printf("swapped %d <=> %d\n", rowToSwap, rowToSwap2);
            }
        }
    }
    // Coming here means there were many attempts of estimating the solution
    // but all the attempts ended up not being accurate enough. It is possible
    // that the epsilon is too small or maybe there's something wrong with the
    // equation.
    printf("The equation set is likely it does not converge.\n");

    /* プログラムの終了 */
    return 0;
}
