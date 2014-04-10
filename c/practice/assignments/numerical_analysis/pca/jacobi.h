/**
 * \mainpage
 *
 * ����Ͽ��Ͳ��� �齬4 �ǻ��Ѥ���ץ����饤�֥��β���Ǥ���
 *
 * jacobi.c, jacobi.h, jsample.c �ϲ��꣱�ǡ�
 * rddata.c, rddata.h, pca.c �ϲ��ꣳ��
 * ���줾����Ѥ��ޤ���
 * 
 */

/**
 * @file   jacobi.h
 * 
 * @brief  �䥳��ˡ�ˤ���оι���θ�ͭ�ͷ׻��ʥإå���
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
