/*
 *
 * ���Ͳ��� �齬(2) �������õ�ˡ�������������ǥ�ˡ�ο����ץ����
 *
 * ���Υ������ե�����ˤϡ�
 *   - ��������ȱ��ե٥��ȥ���ɤ߹���ؿ�,�����
 *   - �������ͤ���Ϥ���ؿ�
 * ���������Ƥ��ޤ���
 *
 * �ɤ߹�����ƥѥ�᡼���ϡ��������Ȥ���
 * �󼡸�������ѿ� "a" �ˡ������� "n" �˳�Ǽ����ޤ���
 *
 * �ޤ��ϡ����Τޤޥ���ѥ��롦�¹Ԥ��ƤߤƤ���������
 * 
 *   % gcc -o sample gauss_template.c
 *   % ./sample
 *   
 * ������ɤ߹���Ǥ��Τޤ޽��Ϥ���ץ���बư���ޤ���
 * �����ư����ǧ���Ƥ���齬�ˤΤ���Ǥ���������
 *
 * �ܺ٤ϥץ������Υ����Ȥ�褯�ɤ�Ǥ���������
 *
 * �ʤ�����ݡ��Ȥ���Ф���Τ� main �ؿ���ΤߤǤ��ޤ��ޤ���
 * �����ϴؿ����ѹ������꼫��Τ�Τ�Ȥä����ϡ��������ѹ�����ޤ��
 * ��Ф��Ƥ���������
 *
 */


/**********************************************************************/
/* �إå��Υ��󥯥롼�ɡ������ͤ����� */
#include <stdio.h>

#define MAXN 20			/* n �κ����� */
#define epsilon 0.000001
#define N 100

/**********************************************************************/
/* ����ѿ� �� �ɤ߹��������ξ���Ϥ����˥��åȤ���� */

/*
 * n ��
 *
 */
int n;

/*
 * �������� A �ȱ��ե٥��ȥ� b ���碌��������� [A b] ���Ǽ���롥
 * A �� a[1][1] ... a[n][n] �ˡ�
 * b �� a[1][n+1] ... a[n][n+1] �˳�Ǽ����롥
 *
 */
double a[MAXN+1][MAXN+2];

/**********************************************************************/
/*
 * ������� m[][] �����Ƥ�ɸ����Ϥ˽��Ϥ���
 *
 * �������ʤ�
 * ���͡��ʤ�
 * ��Ϣ��������ѿ���a[][], n
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
 * ϢΩ�켡�������η�����ɸ�����Ϥ����ɤ߹���ǡ�����ѿ� n �� a[][] ��
 * ��Ǽ���롥
 * A �� a[1][1] ... a[n][n] �ˡ�
 * b �� a[1][n+1] ... a[n][n+1] �ˤ��줾���Ǽ����롥
 *
 * �������ʤ�
 * ���͡��ʤ�
 * ��Ϣ��������ѿ���a[][], n
 *
 */
int
read_matrix()
{
  int j,k;

  /* n ������ */
  printf("n="); scanf("%d", &n);
  if (n > MAXN) {
    printf("n > %d!!\n", MAXN);
    return -1;
  } else if (n < 1) {
    printf("n < 1!!\n");
    return -1;
  }
  
  /* �����ȱ����ͤ򣱹Ԥ������� */
  for(j=1;j<=n;j++) {
    /* a������ */
    for(k=1;k<=n;k++) {
      printf("a_%d%d=", j, k); scanf("%lf", &(a[j][k]));
    }
    /* b������ */
    printf("b_%d =", j); scanf("%lf", &(a[j][n+1]));
  }
  /* ���������Ͻ�λ */

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
    /*  �����˲���Υץ�����񤯡�                          */
    /*                                                          */
    /*  �ؿ� read_matrix() ��Ƥ֤��Ȥǡ�ɸ�����Ϥ���ϢΩ�켡   */
    /*  �������μ�������ӳƷ������ɤ߹��ࡥ���η�̡�          */
    /*  ����������ѿ� n �ˡ�                                   */
    /*  �������� A �ȱ��ե٥��ȥ� b ���碌��������� [A b] �� */
    /*  �Ӱ��ѿ� a[][] �ˡ����줾���Ǽ����롥                 */
    /*  �ʤ���a[][]��������                                     */
    /*      A �� a[1][1] ... a[n][n] ��                         */
    /*      b �� a[1][n+1] ... a[n][n+1]                        */
    /*  �Τ褦�ˤʤäƤ��롥                                    */
    /*                                                          */
    /*  �ؿ� print_matrix() ��  a[][] �����Ƥ�ɸ����Ϥ�        */
    /*  ���Ϥ��롥���르�ꥺ��ΥǥХå��˳��Ѥ��٤���          */
    /*                                                          */
    /************************************************************/


    /* �ʲ��ϥ���ץ�ץ����ǡ�������ɤ߹���Ǥ��Τޤ޽��Ϥ��롥*/

    /* ɸ�����Ϥ��ɤ߹���ǡ���������� a[][] �ˡ������� n �˳�Ǽ���� */
    if (read_matrix() != 0) {	/* �֤��ͤ� 0 �ʳ��Ǥ���Х��顼 */
        fprintf(stderr, "input error!\n");
        return -1;
    }

    /* ���ߤη������� a[][] �����Ƥ���Ϥ��� */
    print_matrix();

    /* �����˥������õ�ˡ�ʤɤβ���Υץ������ */

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

    /* �ץ����ν�λ */
    return 0;
}
