/**
 * @file   rddata.c
 * 
 * @brief  データファイルを配列に読み込む（ソース）
 *
 * □データのフォーマットについて
 *
 * 読み込むことのできる数値データの形式は，1行に1レコードで，
 * <pre>
 * ----------------------
 * X Y ...
 * ----------------------
 * </pre>
 * のように，各フィールドの値が半角空白で区切られたものです．
 * 
 * なお，フィールド数，レコード数は上限なし（メモリの許す限りOK）です．
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>

#define DATASTEP 100		///< データ配列を拡張するステップ数
#define FIELDSTEP 3		///< フィールドを拡張するステップ数

/** 
 * @brief ファイルから標本点のデータ列を読み込む．
 *
 * 読み込んだデータは buf[レコード番号][フィールド番号] として格納されます．
 * 番号は 0 から始まります．たとえば，
 * <pre>
 * ---------------
 * 1,2,3
 * 4,5,6
 * ---------------
 * </pre>
 * という入力の場合，
 * <pre>
 * buf[0][0] = 1, buf[0][1] = 2, buf[0][2] = 3,
 * buf[1][0] = 4, buf[1][1] = 5, buf[1][2] = 6
 * </pre>
 * というように格納されます．
 *
 * @param filename [in] ファイル名
 * @param fnum [out] 読み込んだデータのフィールド数（1行あたりの値の数）を格納する int 変数へのポインタ
 * @param num [out] 読み込んだデータ数（行数）を格納する int 変数へのポインタ
 * 
 * @return 新たに割り付けられ，データが格納された配列へのポインタ．失敗した場合
 * は NULL を返す．フィールド数は fnum, データ数は num に格納して返される．
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
