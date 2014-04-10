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
  
  /* 3. ʬ����ʬ������η׻� */
  
  /* 4. jacobi()�η׻���̤��Ǽ���뤿����ΰ�γ��� */
  
  /* 5. jacobi()��ƤӽФ� */
  
  /* 6. ����줿���Ƥθ�ͭ�٥��ȥ��ʿ�ѥ٥��ȥ뤫��Ƽ���ʬ�μ�����ƽ��� */
  
  /* 7. �Ƹ�ͭ�ͤ��顤�Ƽ���ʬ�δ�ͿΨ����ƽ��� */
  
  /* 8. �ǡ����ˤĤ��Ƥ��줾��μ���ʬ�Ǥμ���ʬ�������ᡤ���� */

  return 0;
}

/* END OF FILE */
