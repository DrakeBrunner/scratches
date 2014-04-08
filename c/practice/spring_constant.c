#include <stdio.h>

int main(int argc, char const* argv[])
{
    double k = 40;          // g / cm
    double x, y;            // x(cm), y(g)

    printf("Enter mass: ");
    scanf("%lf", &y);

    printf("Expansion (x) = %fcm\n", y / k);
    printf("Weight (y) = %fg\n", y);
    return 0;
}
