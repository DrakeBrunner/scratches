/**
 * @file   pca.c
 * 
 * @brief  ����ʬʬ�ϥץ����ο���
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "jacobi.h"
#include "rddata.h"

/** 
 * �ᥤ��ؿ�
 * 
 * @param argc [in] ���ޥ�ɰ����ο�
 * @param argv [in] ���ޥ�ɰ���
 * 
 * @return ���顼�� -1�����ｪλ�� 0 
 */
int main(int argc, char *argv[])
{
    char *filename;
    double **data;
    int n, p;
    int i, j;

    /* ���ޥ�ɰ���̵���ǵ�ư���줿��硤�Ȥ�����ɽ�����ƽ�λ���� */
    if (argc <= 1) {
        printf("usage: %s datafile\n", argv[0]);
        return -1;
    }

    /* ���ϥǡ����ե�����̾����1���ޥ�ɰ����Ȥ��� */
    filename = argv[1];

    /* 1. �ǡ������ɤ߹��� */
    /* �ե�������Υǡ��������� data ���ɤ߹��ࡥ*/
    /* n �˥ե�����ɿ��ʡ�ǡ����μ������ˡ�p �˥ǡ���������Ǽ����� */
    /* data �Υ����ΰ�� rddata() ��ǳ���դ����� */
    data = rddata(filename, &n, &p);

    /* �ɤ߹�����ǡ����򤽤Τޤ޽��� */
    for(i=0;i<p;i++) {
        printf("%d:", i);
        for(j=0;j<n;j++) {
            printf(" %f", data[i][j]);
        }
        printf("\n");
    }

    /* 2. ʿ�ѥ٥��ȥ�η׻� */

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

    /* 3. ʬ����ʬ������η׻� */
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

    /* 4. jacobi()�η׻���̤��Ǽ���뤿����ΰ�γ��� */
    double *eigenvalue = malloc_vector(n);
    double **eigenvector = malloc_matrix(n);

    /* 5. jacobi()��ƤӽФ� */
    jacobi(S, n, 0.00001, eigenvalue, eigenvector, 1000);
    // DEBUG
    /* output_vector(eigenvalue, n); */
    /* output_matrix(eigenvector, n); */

    /* 6. ����줿���Ƥθ�ͭ�٥��ȥ��ʿ�ѥ٥��ȥ뤫��Ƽ���ʬ�μ�����ƽ��� */
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

    /* 7. �Ƹ�ͭ�ͤ��顤�Ƽ���ʬ�δ�ͿΨ����ƽ��� */

    double subTotal = 0;
    for (i = 0; i < n; i++)
        subTotal += S[i][i];

    int k;
    for (k = 0; k < n; k++) {
        double contribution = S[k][k] / subTotal;
        printf("Contribution for component #%d: %f%%\n", k + 1, contribution * 100);
    }
    printf("\n");

    /* 8. �ǡ����ˤĤ��Ƥ��줾��μ���ʬ�Ǥμ���ʬ�������ᡤ���� */
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
