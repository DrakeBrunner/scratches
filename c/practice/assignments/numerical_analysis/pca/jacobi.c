/**
 * @file   jacobi.c
 * 
 * @brief  �䥳��ˡ�ˤ���оι���θ�ͭ�ͷ׻��ʥ�������
 *
 * �䥳��ˡ���Ѥ����оι���θ�ͭ�ͤ�׻�����ؿ��������
 * �����٥��ȥ�Τ�������Ѵؿ������������Ƥ��ޤ���
 * 
 * �����٥��ȥ��ź���� 0 ����Ϥޤ뤳�Ȥ���դ��Ƥ���������
 * �㡧n x n ���������� m �ϡ� m[0..(n-1)][0..(n-1)] �Ȥʤ�ޤ���
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/** 
 * ������������Ƥ�ƥ����Ƚ��Ϥ��롥
 * 
 * @param m [in] n x n ����������ؤΥݥ���
 * @param n [in] @a m �μ���
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
 * �٥��ȥ�����Ƥ�ƥ����Ƚ��Ϥ��롥
 * 
 * @param v [in] n ���Υ٥��ȥ�ؤΥݥ���
 * @param n [in] @a v �μ���
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
 * ���ꤵ�줿��������������Υ����ΰ�򿷤��˳��ݤ��롥
 * 
 * @param n [in] ����μ���
 * 
 * @return �����˳���դ���줿 n x n ����Υ����ΰ�ؤΥݥ���
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
 * ����Υ����ΰ���������
 * 
 * @param m [in] ����ؤΥݥ���
 */
void
free_matrix(double **m)
{
  int i;
  free(m[0]);
  free(m);
}

/**
 * ���ꤵ�줿�����Υ٥��ȥ�򿷤��˳��ݤ��롥
 * 
 * @param n [in] �٥��ȥ�μ���
 * 
 * @return �����˳���դ���줿 n ���Υ٥��ȥ�Υ����ΰ�ؤΥݥ���
 */
double *
malloc_vector(int n)
{
  return ((double *)malloc(sizeof(double) * n));
}

/**
 * �٥��ȥ�Υ����ΰ�����
 *
 * @param v [in] �٥��ȥ�ؤΥݥ���
 */
void
free_vector(double *v)
{
  int i;
  free(v);
}


/**********************************************************************/
/** 
 * @brief  �䥳��ˡ�ˤ���ͭ�͡���ͭ�٥��ȥ�׻�
 *
 * n x n ���оι���θ�ͭ�ͤȸ�ͭ�٥��ȥ�򡤥䥳��ˡ���Ѥ��Ƥ��٤Ƶ��롥
 * itemax ��ʾ�����֤����Ƥ��«Ƚ���� eps ��˼��ޤ�ʤ����ϡ�
 * ��«���ԤȤ��ƥ��顼��λ���롥
 *
 * ����줿��ͭ�ͤ����� @a e_val, ��ͭ�٥��ȥ���󼡸����� @a e_vec ��
 * ��Ǽ����롥������������ malloc ����ʤ��Τǡ��ƤӽФ�����
 * ���� @a n ����ʬ�ΰ���ݤ��Ƥ���ɬ�פ����롥��ͭ�ͤ��礭����˳�Ǽ����롥
 * 
 * @param m [in] ��ͭ�ͤ�����оι��� (n x n)
 * @param n [in] @a m �μ���
 * @param eps [in] ��«Ƚ����
 * @param e_val [out] ����줿��ͭ����ʽ��ϡ�
 * @param e_vec [out] ����줿��ͭ�٥��ȥ���ʽ��ϡ�
 * @param itemax [in] ���緫�֤����
 * @return ��̤�����줿�Ȥ��ޤǤη��֤�������֤������ϥ��顼
 * ����@a m ���оι���Ǥʤ��ʤɡˤ� -1�� @a itemax �󷫤��֤��Ƥ�
 * ��«���ʤ��ä����� -2 ���֤���
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
  
  /* m ���оι��󤫤ɤ��������å� */
  for(i=0;i<n;i++) {
    for(j=i+1;j<n;j++) {
      if (m[i][j] != m[j][i]) {
	printf("matrix is not synmetric!\n");
	return -1;
      }
    }
  }
  /* m �� ����ΰ� a �˥��ԡ� */
  a = malloc_matrix(n);
  for(i=0;i<n;i++) {
    for(j=0;j<n;j++) {
      a[i][j] = m[i][j];
    }
  }
  /* ��ͭ�٥��ȥ�γ�Ǽ���� p (e_vec) ��ñ�̹���˽���� */
  for(i=0;i<n;i++) {
    for(j=0;j<n;j++) {
      e_vec[i][j] = (i == j) ? 1.0 : 0.0;
    }
  }
  /* �����󥿥ꥻ�å� */
  count = 0;
  
  /* ��«Ƚ���ͤޤǥ롼�� */
  while(1) {
    /* a[][] ��������ͺ�������Ǥ򸫤Ĥ���ʾ廰����ǡ� */
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
    /* �������Ǥ��ͤ� eps �ʲ��ʤ��«�Ȥ��ƽ�λ */
    if (max_e <= eps) {
      ret = count;
      break;
    }
    /* ���֤������ itemax �ʾ�ˤʤä��餢�����ƽ�λ */
    if (count >= itemax) {
      printf("loop reached %d!\n", itemax);
      ret = -2;
      break;
    }

    /* a[r][c] �� 0 �ˤ���褦�ʲ�ž���� P_k ��׻����� */
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

    /* a[][] ����� p[][] (e_vec[]) �� P_k �򤫤��ƹ���  */
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
    
    /* �����󥿤򥤥󥯥���� */
    count++;
  }
  
  /* ��ͭ�ͤ� a ���г���ʬ�Ȥ���������Τǡ������ѥХåե��˥��ԡ� */
  for(i=0;i<n;i++) {
    e_val[i] = a[i][i];
  }
  /* ��ͭ�٥��ȥ�� e_vec[����][��ͭ��ID] �η��ǳ�Ǽ����Ƥ���Τǡ�
     ž�֤��� e_vec[��ͭ��ID][����] �ˤ��Ƥ��� */
  for(i=0;i<n;i++) {
    for(j=i+1;j<n;j++) {
      tmp = e_vec[i][j];
      e_vec[i][j] = e_vec[j][i];
      e_vec[j][i] = tmp;
    }
  }
  /* ��ͭ�ͤ��礭����˸�ͭ���󡦸�ͭ�٥��ȥ���򥽡��Ȥ���ʥХ֥륽���ȡ� */
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

  /* ����ΰ����� */
  free_matrix(a);

  return (ret);
}

/* END OF FILE */
