/**
 * @file   rddata.c
 * 
 * @brief  �ǡ����ե������������ɤ߹���ʥ�������
 *
 * ���ǡ����Υե����ޥåȤˤĤ���
 *
 * �ɤ߹��ळ�ȤΤǤ�����ͥǡ����η����ϡ�1�Ԥ�1�쥳���ɤǡ�
 * <pre>
 * ----------------------
 * X Y ...
 * ----------------------
 * </pre>
 * �Τ褦�ˡ��ƥե�����ɤ��ͤ�Ⱦ�Ѷ���Ƕ��ڤ�줿��ΤǤ���
 * 
 * �ʤ����ե�����ɿ����쥳���ɿ��Ͼ�¤ʤ��ʥ���ε����¤�OK�ˤǤ���
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>

#define DATASTEP 100		///< �ǡ���������ĥ���륹�ƥå׿�
#define FIELDSTEP 3		///< �ե�����ɤ��ĥ���륹�ƥå׿�

/** 
 * @brief �ե����뤫��ɸ�����Υǡ�������ɤ߹��ࡥ
 *
 * �ɤ߹�����ǡ����� buf[�쥳�����ֹ�][�ե�������ֹ�] �Ȥ��Ƴ�Ǽ����ޤ���
 * �ֹ�� 0 ����Ϥޤ�ޤ������Ȥ��С�
 * <pre>
 * ---------------
 * 1,2,3
 * 4,5,6
 * ---------------
 * </pre>
 * �Ȥ������Ϥξ�硤
 * <pre>
 * buf[0][0] = 1, buf[0][1] = 2, buf[0][2] = 3,
 * buf[1][0] = 4, buf[1][1] = 5, buf[1][2] = 6
 * </pre>
 * �Ȥ����褦�˳�Ǽ����ޤ���
 *
 * @param filename [in] �ե�����̾
 * @param fnum [out] �ɤ߹�����ǡ����Υե�����ɿ���1�Ԥ�������ͤο��ˤ��Ǽ���� int �ѿ��ؤΥݥ���
 * @param num [out] �ɤ߹�����ǡ������ʹԿ��ˤ��Ǽ���� int �ѿ��ؤΥݥ���
 * 
 * @return �����˳���դ���졤�ǡ�������Ǽ���줿����ؤΥݥ��󥿡����Ԥ������
 * �� NULL ���֤����ե�����ɿ��� fnum, �ǡ������� num �˳�Ǽ�����֤���롥
 */
double **
rddata(char *filename, int *fnum, int *num)
{
  FILE *fp;
  int r, n;
  static char inbuf[1024];
  char *p;
  int i;
  double **buf, *cur;
  int max_n;

  if ((fp = fopen(filename, "r")) == NULL) {
    printf("Error: read_data: fopen failed for \"%s\"\n", filename);
    return NULL;
  }

  max_n = DATASTEP;
  if ((buf = (double **)malloc(sizeof(double *) * max_n)) == NULL) {
    printf("Error: read_data: malloc error 1 (%d)\n", max_n);
    return NULL;
  }
  r = 5; n = 0;
  while (fgets(inbuf, 1024, fp) != NULL) {
    
    if ((cur = (double *)malloc(sizeof(double) * r)) == NULL) {
      printf("Error: read_data: malloc error 2 (%d)\n", r);
      return NULL;
    }
    i = 0;
    for(p = strtok(inbuf, " \t\n"); p; p = strtok(NULL, " \t\n")) {
      if (i >= r) {
	if (n == 0) {
	  r += 5;
	  if ((cur = (double *)realloc(cur, sizeof(double) * r)) == NULL) {
	    printf("Error: read_data: malloc error 3 (%d)\n", r);
	    return NULL;
	  }
	} else {
	  printf("Error: read_data: bad data at line %d in \"%s\"!\n", n + 1, filename);
	  fclose(fp);
	  return NULL;
	}
      }
      cur[i++] = atof(p);
    }
    if (n == 0) {
      r = i;
    } else if (r != i) {
      printf("Error: read_data: bad data at line %d in \"%s\"!\n", n + 1, filename);
    }

    if (n >= max_n) {
      max_n += DATASTEP;
      if ((buf = (double **)realloc(buf, sizeof(double *) * max_n)) == NULL) {
	printf("Error: read_data: malloc error 4 (%d)\n", max_n);
	return NULL;
      }
    }
    buf[n++] = cur;
  }

  if (fclose(fp) != 0) {
    printf("Error: read_data: fclose failed for \"%s\"\n", filename);
    return NULL;
  }

  *fnum = r;
  *num = n;

  return(buf);
}

/* END OF FILE */
