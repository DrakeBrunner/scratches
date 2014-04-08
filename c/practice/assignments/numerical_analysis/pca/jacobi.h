/**
 * \mainpage
 *
 * これは数値解析 演習4 で使用するプログラムライブラリの解説です．
 *
 * jacobi.c, jacobi.h, jsample.c は課題１で，
 * rddata.c, rddata.h, pca.c は課題３で
 * それぞれ使用します．
 * 
 */

/**
 * @file   jacobi.h
 * 
 * @brief  ヤコビ法による対称行列の固有値計算（ヘッダ）
 * 
 */

#ifndef __JACOBI_H__
#define __JACOBI_H__

void output_matrix(double **m, int n);
void output_vector(double *v, int n);
double **malloc_matrix(int n);
double *malloc_vector(int n);
void free_matrix(double **m);
void free_vector(double *v);
int jacobi(double **m, int n, double eps, double *e_val, double **e_vec, int itemax);

#endif /* __JACOBI_H__ */
