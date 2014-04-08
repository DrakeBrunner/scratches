// コンパイルに失敗するときは
// gcc struct3.c -L/usr/include -lm
// でコンパイルしてみる
#include <math.h>
#include <stdio.h>
/* 2乗値を求める */
#define sqr(n)((n) * (n))

/* 点 */
typedef struct {
    /* X座標 */
    int x;
    /* Y座標 */
    int y;
} point;

/* 3辺の長さを格納する構造体 */
typedef struct {
    /* 点1と点2の長さ */
    double ab;
    /* 点2と点3の長さ */
    double bc;
    /* 点3と点1の長さ */
    double ca;
} tri_point;

/* 点paとpbの距離を返す */
double distanceof(point pa, point pb) {
    // START

    // Distance of 2 points is,
    // sqrt( (x_1 - x_2)^2 + (y_1 - y_2)^2 )
    //          square_x        square_y

    // x, yそれぞれの2乗を求める
    double square_x = sqr(pa.x - pb.x);
    double square_y = sqr(pa.y - pb.y);

    double distance = sqrt(square_x + square_y);

    return distance;
}

/* 3辺から三角形の面積を返す */
double triangleof(tri_point tri) {
    // START

    // The Heron's formula is,
    // Let s = (a + b + c) / 2
    // then
    // area = sqrt( s * (s - a) * (s - b) * (s - c) )
    double s = (tri.ab + tri.bc + tri.ca) / 2;
    double area = sqrt( s * (s - tri.ab) * (s - tri.bc) * (s - tri.ca) );

    return area;
}

int main(void) {
    point p1, p2, p3;
    tri_point tp;

    scanf("%d", &p1.x);
    printf("点1のX座標:%d\n", p1.x);

    scanf("%d", &p1.y);
    printf("     Y座標:%d\n", p1.y);

    scanf("%d", &p2.x);
    printf("点2のX座標:%d\n", p2.x);

    scanf("%d", &p2.y);
    printf("     Y座標:%d\n", p2.y);

    scanf("%d", &p3.x);
    printf("点3のX座標:%d\n", p3.x);

    scanf("%d", &p3.y);
    printf("     Y座標:%d\n", p3.y);

    /* 3辺の長さを構造体に格納 */

    // START
    tp.ab = distanceof(p1, p2);
    tp.bc = distanceof(p2, p3);
    tp.ca = distanceof(p3, p1);


    printf("それらの点によって作られる三角形の面積は%.2fです。\n", triangleof(tp));

    return 0;
}
