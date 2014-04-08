/*****
 * Author   : inclezd (modification of code from karroje and brinkmfwj) 
 * Date     : 2013-08-30
 * Purpose  : These functions are utility functions for generating bmp formatted fractals.
 * NOTES    : DO NOT MODIFY ANY OF THE CODE I PROVIDED! Write your code below the line at the bottom of this file.
 *****/
#include <iostream>
#include <fstream>

//Windows.h is only needed for readHeader and writeHeader.
#include <Windows.h>
#include "BMPCanvas.h"
#include "Fractal.h"
#include <math.h>
#include <complex>

#pragma comment (lib, "winmm.lib")

using namespace std;

/**
 * Provided functions. DO NOT MODIFY!!!
 **/

// Random Number Generator seed: Seed the random number generator.  Seeds
// from clock if seed_value = 0.
void seedRNG(long seed_value) {
  if (seed_value > 0) 
      srand(seed_value);
  else {
     double x = timeGetTime();  
     while (x == timeGetTime() );   // BETTER solution: get process ID
    srand(timeGetTime());
  }
}


// Return a random integer in the half-open interval [i,j).
// (Meaning: it returns a number ranging from i up to BUT NOT INCLUDING j.)
int randomNumber(int i, int j) {
    return (int)((j-i)*((1.0*rand()) / (RAND_MAX+1))) + i;
}

//Information on BMP format was provided by www.fileformat.info/format/bmp/egff.htm
void writeHeader(ostream& out, int width, int height){
  if (width % 4 != 0) {
    cerr << "ERROR: There is a windows-imposed requirement on BMP that the width be a multiple of 4.\n";
    cerr << "Your width does not meet this requirement, hence this will fail.  You can fix this\n";
    cerr << "by increasing the width to a multiple of 4." << endl;
    exit(1);
  }

    BITMAPFILEHEADER tWBFH;
    tWBFH.bfType = 0x4d42;
    tWBFH.bfSize = 14 + 40 + (width*height*3);
    tWBFH.bfReserved1 = 0;
    tWBFH.bfReserved2 = 0;
    tWBFH.bfOffBits = 14 + 40;

    BITMAPINFOHEADER tW2BH;
    memset(&tW2BH,0,40);
    tW2BH.biSize = 40;
    tW2BH.biWidth = width;
    tW2BH.biHeight = height;
    tW2BH.biPlanes = 1;
    tW2BH.biBitCount = 24;
    tW2BH.biCompression = 0;

    out.write((char*)(&tWBFH),14);
    out.write((char*)(&tW2BH),40);
}

//Information on BMP format was provided by www.fileformat.info/format/bmp/egff.htm
void readHeader(istream& in, int& width, int& height){
    BITMAPFILEHEADER tWBFH;
    BITMAPINFOHEADER tW2BH;
    in.read((char*)(&tWBFH),14);
    in.read((char*)(&tW2BH),40);

    width = tW2BH.biWidth;
    height = tW2BH.biHeight;
}

/*****************************************************************************************************/
/**
 * End of provided functions, your work goes below here. You may modify anything below this line, but
 * do NOT modify anything above this line.
 **/

void helloworld() {
    std::cout << "Hello, World!" << std::endl;
}

void MakeSquare256() {
    int width = 256;
    int height = 256;
    BMPCanvas b("square256", width, height);

    int x, y;
    Color black(0, 0, 0);
    Color white(255, 255, 255);
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            if (x >= 64 && x < 192 && y >= 64 && y < 192)
                b.setPixel(x, y, white);
            else
                b.setPixel(x, y, black);
        }
    }

    b.writeBMP();
}

void MakeSphere256() {
    int width = 256;
    int height = 256;
    BMPCanvas b("sphere256", width, height);

    int x, y;
    Color black(0, 0, 0);
    Color white(255, 255, 255);
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            int distanceSquared = (x - 128) * (x - 128) + (y - 128) * (y - 128);
            int color_value = 0;
            if(distanceSquared <= (90 * 90)) {
                color_value = (255 * ((90 * 90) - distanceSquared)) / (90 * 90);
            }
            Color c(color_value, color_value, color_value);   // Keeping all values the same dictates a shade of grey

            b.setPixel(x, y, c);
        }
    }

    b.writeBMP();
}


/* Phase 3 */
Complex Point2Complex(int i, int j, double reMin, double reMax, int width, double imMin, double imMax, int height) {
     double a = reMin + i * (reMax-reMin) / (width - 1.0);
     double b = imMin + j * (imMax-imMin) / (height - 1.0);

     return Complex(a, b);
}

/*
    Helper method that calculates z(i, c) according to the rule in header file.
*/
inline Complex z(int i, Complex c, Complex k) {
    if (i == 0)
        return c;

    Complex tmp = z(i - 1, c, k);
    return tmp * tmp + k;
}

/*
    Called a lot of times in Julia.
*/
inline int FractalDivergeCount(Complex c, Complex k, int numIterations) {
    int i;
    for (i = 0; i < numIterations; i++) {
        Complex result_of_z = z(i, c, k);
        if (abs(result_of_z) > 2)
            return i;
    }
    return numIterations;
}

/*
    Given the width of the canvas, this method first determines the
    coordinates of the three points of an equilateral triangle. Provided that
    the first and second points must be (0, 0) and (width - 1, 0),
    respectively, the third point can be calculated using trigonometry.
    The method will generate a BMP image file with the given file name.

    After calculating the three points, the method picks two random points
    from those three points. Let them be p and q. The color of the pixel at the
    midpoint of p and q is replaced with a random color. p is replaced with the
    midpoint of the two points. This is repeated n times.

    The height of the canvas is determined by the point that is at the "highest"
    coordinate. In particular, it will be tan(60 deg) * (width / 2).

    @param fileName The name of the file generated.
    @param width The width of the canvas.
    @param n The number of times the color is set at point p.
*/
void Sierpinski(string fileName, int width, int n) {
    const double PI = 3.14159265358979323846;
    int yMax = (int)(tan(60 * PI / 180) * (width / 2));
    // p2 = (width / 2, yMax)

    BMPCanvas b(fileName, width, yMax);
    b.blank(false);

    int x[] = { 0, width - 1, width / 2 };
    int y[] = { 0, 0, yMax };

    seedRNG();
    // Choose a random point from the three inital points
    int rand = randomNumber(0, 3);
    int p_x = x[rand];
    int p_y = y[rand];

    int i;
    for (i = 0; i < n; i++) {

        Color c(randomNumber(0, 256), randomNumber(0, 256), randomNumber(0, 256));
        // Color c(255, 255, 255);
        b.setPixel(p_x, p_y, c);

        // Generate new random number between 0 and 2
        rand = randomNumber(0, 3);
        int q_x = x[rand];
        int q_y = y[rand];

        // Find midpoint of p and q and replace p with the midpoint
        p_x = (p_x + q_x) / 2;
        p_y = (p_y + q_y) / 2;
    }

    b.writeBMP();
}

void Julia(string fileName, Complex k) {
    const int width = 1000;
    const int height = 1000;
    const double reMin = -1.5;
    const double imMin = -1.5;
    const double reMax = 1.6;
    const double imMax = 1.6;

    BMPCanvas b(fileName, width, height);
    b.blank(true);

    int i, j;
    for (j = 0; j < height; j++) {
        for (i = 0; i < width; i++) {
            Complex c = Point2Complex(i, j, reMin, reMax, width, imMin, imMax, height);

            int f = FractalDivergeCount(c, k, 100);
            // cout << "f = " << f << endl;

            Color color;
            if (f < 50)
                color = Color(0, 9, 5 * f);
            else
                color = Color(f - 50, 10 * (f - 50), 255);

            b.setPixel(i, j, color);
        }
    }

    b.writeBMP();
}

void Mandelbrot(string fileName) {
    const int width = 1000;
    const int height = 1000;
    const double reMin = -2.0;
    const double imMin = -1.5;
    const double reMax = 1.0;
    const double imMax = 1.5;

    BMPCanvas b(fileName, width, height);
    b.blank(true);

    int i, j;
    for (j = 0; j < height; j++) {
        for (i = 0; i < width; i++) {
            Complex c = Point2Complex(i, j, reMin, reMax, width, imMin, imMax, height);

            // Only thing different from Julia
            int f = FractalDivergeCount(c, c, 100);

            Color color;
            if (f < 50)
                color = Color(0, 9, 5 * f);
            else
                color = Color(f - 50, 10 * (f - 50), 255);

            b.setPixel(i, j, color);
        }
    }

    b.writeBMP();
}
