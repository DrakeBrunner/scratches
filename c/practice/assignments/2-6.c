#include <stdio.h>

int main()
{
    int height;

    printf("Input height: ");
    scanf("%d", &height);

    printf("Your standard weight is %.1f\n", (height - 100) * .9);

    return 0;
}
