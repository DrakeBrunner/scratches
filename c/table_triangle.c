#include<stdio.h>
int main() {
	int a, b, max;

	printf("Input number of the table: ");
	scanf("%d", &max);

	for (a = 1; a <= max; a++){
        for (b = 1; b < a; b++)
			printf("    ");
		for (b = a; b <= max; b++)
			printf("%4d", a * b);
        printf("|%4d", a);
        putchar('\n');
	}
    putchar('\n');

	return 0;
}
