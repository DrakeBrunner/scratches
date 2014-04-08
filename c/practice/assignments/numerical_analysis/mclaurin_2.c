#include <stdio.h>

int main(void) {

    int va[100] = {0};
    int vb[100] = {0};
    int vc[100] = {0};
    va[0] = 1;
    vc[0] = 1;
    int no;
    int i, j, k, m, n;

    printf("展開する項数を入力してください：");
    scanf("%d", &no);

    for(i = 1; i <= no; i++) {
        for(j = 0; j < 100; j++) {
            // １番上の桁を割る
            vb[j] = va[j] / i;
            // 次の桁に、割った余りを10倍したものを足す。
            va[j + 1] = va[j + 1] + ((va[j] % i) * 10);
        }

        // 一通り割り算をしていく
        for(k = 99; k >= 0; k--) {
            // 下の桁から桁上がりした 数を足しておく。
            vc[k - 1] = vc[k - 1] + ((vc[k] + vb[k]) / 10);
            // １番下の桁から足し算をしていく。
            vc[k] = (vc[k] + vb[k]) % 10;
        }

        for(m = 0; m < 100; m++)
            // 次の無限級数展開を行うために値を渡す。
            va[m] = vb[m];
    }

    // Finally, print all what's in the array
    printf("%d.", vc[0]);
    for(n = 1; n < 100; n++)
        printf("%d", vc[n]);
    putchar('\n');

    return 0;
}
