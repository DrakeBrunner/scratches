// 参考
// tm構造体
//      http://www.c-tipsref.com/reference/time/tm.html
// localtime関数
//      http://www.c-tipsref.com/tips/time/localtime.html#sample
// もうひとつの例
//      http://www9.plala.or.jp/sgwr-t/lib/localtime.html

#include <time.h>
#include <stdio.h>

/* Determine the date, year, month, and AM/PM */
void put_time(void) {
    // START

    //       ↓名前変更不可  ↓好きな名前付けれる
    // まずはtime_t型のcurrentを作る(int型のxを作るのと同じ)
    time_t current;

    // ↓名前は変えられない       ↓好きな名前でOK
    // tmという構造体へのポインタlocalを作る(この時点でlocalは空っぽ)
    struct tm *local;

    // 関数timeを使い、現在の時間をcurrentに入れる
    time(&current);

    // 関数localtimeを使い、currentを地方時間に変えて、構造体localに代入する
    local = localtime(&current);

    // ポインタなのでアロー演算子(->)を使う
    int year    = local -> tm_year + 1900;
    int month   = local -> tm_mon + 1;
    int date    = local -> tm_mday;
    // 午前午後を判断するため
    int hour    = local -> tm_hour;
    char *ampm;
    if (hour < 12)
        ampm = "午前";
    else
        ampm = "午後";

    printf("%d年%d月%d日%s", year, month, date, ampm);
}

int main(void) {
    printf("現在は");
    put_time();
    printf("です。\n");

    return 0;
}
