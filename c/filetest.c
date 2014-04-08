// This program is for testing file opening and closing
// Made by Naoki Mizuno (nm2372@gmail.com)

#include <stdio.h>

int main(void) {
    FILE *FILEHANDLE;
    char filename[60], line[60];

    puts("This program will open and display the file you specified.");
    printf("Enter filename: \n");
    scanf("%s", filename);


    if ((FILEHANDLE = fopen(filename, "r")) == NULL) {
        printf("Could not open file \"%s\".\n", filename);
    }
    else {
        printf("This is the content of \"%s\"\n", filename);
        while (fscanf(FILEHANDLE, "%s", line) > 0) {
            printf("%s", line);
        }
        printf("\n--------------------------------\nFile ends here.\n");
    }

    fclose(FILEHANDLE);

    return 0;
}
