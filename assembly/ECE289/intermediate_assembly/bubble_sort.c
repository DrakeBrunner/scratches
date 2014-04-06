#include <stdio.h>
#include <stdlib.h>

#define DEBUG

// *THE* bubble sort
void BubbleSort(int a[], int array_size) {
	int i, j, temp;
	for (i = 0; i < (array_size - 1); ++i) {
		for (j = 0; j < array_size - 1 - i; ++j ) {
			if (a[j] > a[j+1]) {
				temp = a[j+1];
				a[j+1] = a[j];
				a[j] = temp;
			}
		}
	}
}

int main(int argc, char const* argv[]) {
    int i;
    int num = 8;
    int * a = (int *)malloc(sizeof(int) * num);

    for (i = 0; i < num; i++)
        scanf("%d", a + i);

#ifdef DEBUG
    printf("Before\n");
    for (i = 0; i < num; i++)
        printf("%d ", a[i]);
    printf("\n");
#endif

    BubbleSort(a, num);

#ifdef DEBUG
    printf("After\n");
    for (i = 0; i < num; i++)
        printf("%d ", a[i]);
    printf("\n");
#endif

    return 0;
}
