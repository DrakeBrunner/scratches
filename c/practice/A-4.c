#include <stdio.h>

int main () {
    int hour, min, sec;

    printf("Input in seconds: "); scanf("%d", &sec);

    min = sec / 60;
    sec -= min * 60;
    hour = min / 60;
    min -= hour * 60;

    printf("%d hour(s) %d minute(s) %d second(s)\n", hour, min ,sec);

    return 0;
}
