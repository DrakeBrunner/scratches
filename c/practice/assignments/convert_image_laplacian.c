// This program converts .pgm (gray scale) files to
// .pbm (black and white) files.
// Currently, comment lines starting with # will cause
// an unexpected error.

#include <stdio.h>
#include <string.h>
#define THRESHOLD 90

int L[3][3] =  {{0,  1,  0},
                {1, -4,  1},
                {0,  1,  0}};
int width = 0;

int abs(int num) {
    return (num > 0) ? num : -1 * num;
}

void edge_l(int content[][width], int content_out[][width], int i) {
    content_out[i][0] = ( L[1][1] * content[i][0] + L[0][1] * content[i-1][0]
            + L[2][1] * content[i+1][0] + L[1][2] * content[i][1] );
}

void edge_r(int content[][width], int content_out[][width], int i) {
    int last = width - 1;
    content_out[i][last] = ( L[1][1] * content[i][last] + L[0][1] * content[i-1][last]
            + L[2][1] * content[i+1][last] + L[1][0] * content[i][last-1] );
}

int main(void) {
    // Brightness info
    int value;

    // Header in output file
    char type[5];
    int height; // width is declared as global
    int max = 0;

    char filename[100];
    FILE *file_handle;
    FILE *output;

    int i = 0;
    int j = 0;
    int center = 0;

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
    if (strcmp(type, "P2") != 0) {
        printf("Invalid type of file (wrong header)\n");
        return 1;
    }

    // Change P2 to P1
    type[1] = '1';

    // Map brightness info to 2-dimensional array
    int content[height][width];
    int content_out[height][width];
    for (i = 0; i < height; i++) {
        for (j = 0; j < width; j++) {
            if (fscanf(file_handle, "%d", &value) == 1)
                content[i][j] = value;
        }
    }

    // Output to file
    fprintf(output, "%s\n%d %d\n%d\n", type, width, height, max);
    // Array for output
    // Top
    content_out[0][0] = L[1][1] * content[0][0] + L[1][2] * content[0][1]
        + L[2][1] * content[1][0];
    for (i = 1; i < width - 1; i++)
        content[0][i] = L[1][1] * content[0][i] + L[1][2] * content[0][i+1]
            + L[1][0] * content[0][i-1] + L[2][1] * content[1][i];
    content_out[0][width-1] = L[1][1] * content[0][width-1] + L[1][0] * content[0][width-2]
        + L[2][1] * content[1][width-1];
    // End of Top

    for (i = 1; i < height - 1; i++) {
        // Edge (left side)
        edge_l(content, content_out, i);

        for (j = 1; j < width - 1; j++) {
            // Filter
            content_out[i][j] = (L[1][1] * content[i][j] + L[0][1] * content[i-1][j]
                    + L[2][1] * content[i+1][j] + L[1][0] * content[i][j-1]
                    + L[1][2] * content[i][j+1]);
        }

        // Edge (right side)
        edge_r(content, content_out, i);
    }

    // Bottom
    int last = height - 1;
    content_out[last][0] = L[1][1] * content[last][0] + L[1][2] * content[last][1]
        + L[0][1] * content[last-1][0];
    for (i = 1; i < width - 1; i++)
        content[last][i] = L[1][1] * content[last][i] + L[1][2] * content[last][i+1]
            + L[1][0] * content[last][i-1] + L[0][1] * content[last-1][i];
    content_out[last][width-1] = L[1][1] * content[last][width-1] + L[1][0] * content[last][width-2]
        + L[0][1] * content[last-1][width-1];
    // End of Bottom

    // After that, make it black and white!
    for (i = 0; i < height; i++) {
        for (j = 0; j < width; j++) {
            if (abs(content_out[i][j]) > THRESHOLD)
                fprintf(output, "%d\n", 1);
            else
                fprintf(output, "%d\n", 0);
        }
    }

    // Close
    fcloseall();

    return 0;
}
