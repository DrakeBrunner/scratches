#include <stdio.h>

// returns the number of 1's in a word in it's binary representation
unsigned int popCnt(unsigned int input) { 
    unsigned int sum = 0; 

    int i;
    for (i = 0; i < 32; i++) {
        sum += (input) & 1; 
        input = input/2; 
    } 

    return sum; 
}

int main(int argc, char const* argv[]) {
    int num = 5;
    printf("%d\n", popCnt(num));
    return 0;
}
