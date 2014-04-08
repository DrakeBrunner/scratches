#include <stdio.h>
#include <math.h>

int main(void) {
    double base, height_triangle, width, height_rectangle, upper, lower, height_trapezoid, radius;

    printf("Enter the following parameters\n");
    printf("Base of triangle\n\t->");   scanf("%lf", &base);
    printf("Height of triangle\n\t->"); scanf("%lf", &height_triangle);

    printf("Width of rectangle\n\t->"); scanf("%lf", &width);
    printf("Height of rectangle\n\t->"); scanf("%lf", &height_rectangle);

    printf("Upper of trapezoid\n\t->"); scanf("%lf", &upper);
    printf("Lower of trapezoid\n\t->"); scanf("%lf", &lower);
    printf("Height of trapezoid\n\t->"); scanf("%lf", &height_trapezoid);

    printf("Radius of circle\n\t->"); scanf("%lf", &radius);

    printf("Area of triangle:\t%f\n", (base * height_triangle) / 2);
    printf("Area of rectangle:\t%f\n", width * height_rectangle);
    printf("Area of trapezoid:\t%f\n", (upper + lower) * height_trapezoid / 2);
    printf("Area of circle:\t%f\n", M_PI * pow(radius, 2));

    return 0;
}
