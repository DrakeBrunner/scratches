#include <iostream>
#include <string>
using namespace std;

int pivot(int a[], int left, int right);
int partition(int a[], int left, int right);
void quicksort(int a[], int len);

int main() {
    int a[] = {863, 52, 628, 888, 32, 303, 607, 388, 944, 91};
    int len = sizeof(a) / sizeof(int);
    int i;
    for (i = 0; i < len; i++)
        cout << a[i] << " ";
    cout << endl;

    quicksort(a, len);

    for (i = 0; i < len; i++)
        cout << a[i] << " ";
    cout << endl;

    return 0;
}

int pivot(int a[], int left, int right) {
    int middle = (left + right) / 2;

    if (a[left] > a[right])
        return a[middle] > a[left] ? left : a[middle] > a[right] ? middle : right;
    else
        return a[middle] > a[right] ? right : a[middle] > a[left] ? middle : left;
}

// Sorts within the given range
// Returns the index of the next center
int partition(int a[], int left, int right) {
    int p = pivot(a, left, right);

    // Swap left and pivot
    int tmp = a[left];
    a[left] = a[p];
    a[p] = tmp;

    p = left;
    int i = left + 1;
    int j = right;

    while (true) {
        while (a[i] < a[p])
            i++;
        while (a[j] > a[p])
            j--;

        if (i >= j)
            break;

        // Swap i and j
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

        i++;
        j--;
    }

    // Swap j and p
    tmp = a[j];
    a[j] = a[p];
    a[p] = tmp;

    return j;
}

void quicksort(int a[], int len) {
    int left = 0;
    int right = len - 1;

    int l_stack[32];
    int r_stack[32];
    int stack = 0;

    l_stack[stack] = left;
    r_stack[stack] = right;
    stack++;

    while (stack > 0) {
        stack--;
        left = l_stack[stack];
        right = r_stack[stack];

        if (right - left <= 0)
            continue;

        // Do a bubble sort
        if (right - left <= 4) {
            int i, j;
            for (i = 0; i < len; i++) {
                for (j = i; j > 0; j--) {
                    if (a[j - 1] > a[j]) {
                        int tmp = a[j - 1];
                        a[j - 1] = a[j];
                        a[j] = tmp;
                    }
                }
            }
            continue;
        }

        int center = partition(a, left, right);

        if (center + 1 < right) {
            l_stack[stack] = center + 1;
            r_stack[stack] = right;
            stack++;
        }
        else if (left < center - 1) {
            l_stack[stack] = left;
            r_stack[stack] = center - 1;
            stack++;
        }
    }
}
