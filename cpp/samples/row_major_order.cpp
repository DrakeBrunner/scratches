#include <iostream>
#include <cstdio>
#include <string>
using namespace std;

int getWidth(int n) {
    int width = 0;
    while (n > 0) {
        width++;
        n /= 10;
    }
    return width;
}

int main(int argc, char const* argv[]) {
    int n;
    cin >> n;

    // The width used when using printf
    int width = getWidth(n * 2 - 2);

    int * a = new int[n * n];

    int i, j;
    for (j = 0; j < n; j++) {
        for (i = 0; i < n; i++) {
            a[n * j + i] = i + j;
        }
    }

    for (i = 0; i < n * n; i++) {
        printf("%*d ", width, a[i]);

        if (i % n == n - 1)
            cout << endl;
    }
    return 0;

    delete[] a;
}
