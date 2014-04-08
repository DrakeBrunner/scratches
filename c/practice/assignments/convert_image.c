// This program converts .pgm (gray scale) files to
// .pbm (black and white) files.
// Currently, comment lines starting with # will cause
// an unexpected error.

#include <stdio.h>
#include <string.h>

int main(void) {
    // Brightness info
    int value;

    // Header in output file
    char type[5];
    int width, height;
    int max = 0;

    int i = 0;
    // For threshold
    double average = 0;

    char filename[100];
    FILE *file_handle;
    FILE *output;


    printf("File name: ");
    scanf("%s", filename);

    // Open files
    // Exit when file open fails
    if ( (file_handle = fopen(filename, "r")) == NULL ) {
        printf("Could not open input file!\n");
        return 1;
    }
    if ((output = fopen("output.pbm", "w")) == NULL) {
        printf("Could not open output file!\n");
        return 1;
    }

    // Get header and stop if file type is not P2
    fscanf(file_handle, "%s%d%d%d", type, &width, &height, &max);
// DEBUG
// printf("type: %s width: %d height: %d max: %d\n", type, width, height, max);
    if (strcmp(type, "P2") != 0) {
        printf("Invalid type of file (wrong header)\n");
        return 1;
    }

    // Change P2 to P1
    type[1] = '1';

    // Get average and set as threshold
    int content[width * height];
    while (fscanf(file_handle, "%d", &value) == 1) {
        content[i++] = value;
        average += value;
    }
    average /= i;

// DEBUG
// printf("average: %f width * height: %d i: %d\n", average, width * height, i);

    fprintf(output, "%s\n%d %d\n%d\n", type, width, height, max);
    for (i = 0; i < width * height; i++) {

        if (content[i] < average)
            fprintf(output, "%d\n", 1);
        else
            fprintf(output, "%d\n", 0);
    }

    fcloseall();

    return 0;
}
