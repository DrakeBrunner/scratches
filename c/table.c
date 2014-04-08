#include<stdio.h>
int main()
{
	int a, b, max;

	printf("Input number of the table: ");
	scanf("%d", &max);

	for(a = 1; a <= max; a++){
		for(b = 1; b <= max; b++){
			printf("%4d", a * b);
		}
		printf("\n");
	}
	printf("\n");

	return 0;
}
