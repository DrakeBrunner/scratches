#include <iostream>
#include <fstream>
#include "BMPCanvas.h"
#include "Fractal.h"

BMPCanvas::BMPCanvas(string iFileName, int iWidth, int iHeight) {
    fileName = iFileName;
    width = iWidth;
    height = iHeight;

    image = new Color[width * height];
};

BMPCanvas::BMPCanvas(string iFileName) {
    fileName = iFileName;
    read();
};

BMPCanvas::BMPCanvas() {
    width = height = 0;
    image = NULL;
    fileName = "";
};

BMPCanvas::~BMPCanvas() {
    delete[] image;
};

void BMPCanvas::read() {
    ifstream file(fileName + ".bmp", ios::in|ios::binary);

    // file.seekg(0, file.end);
    // int length = (int)file.tellg();
    // file.seekg(0, file.beg);

    readHeader(file, width, height);
    // De-allocate image
    delete[] image;
    // Allocate image
    image = new Color[width * height];

    char * buf = new char[3 * width * height];

    file.read(buf, 3 * width * height);

    int i;
    for (i = 0; i < width * height; i++)
        image[i] = Color(buf[3 * i], buf[3 * i + 1], buf[3 * i + 2]);
}

void BMPCanvas::writeBMP() {
    ofstream file(fileName + ".bmp", ios::out|ios::binary);
    writeHeader(file, width, height);

    int length = 3 * width * height;
    char * buf = new char[length];

    int i;
    for (i = 0; i < width * height; i++) {
        buf[3 * i] = image[i].red;
        buf[3 * i + 1] = image[i].green;
        buf[3 * i + 2] = image[i].blue;
    }

    file.write(buf, length);
}

void BMPCanvas::setDims(int iWidth, int iHeight) {
    width = iWidth;
    height = iHeight;
}

int BMPCanvas::getWidth() {
    return width;
}

int BMPCanvas::getHeight() {
    return height;
}

void BMPCanvas::setPixel(int x, int y, Color c) {
    if (x <= width && y <= height)
        image[y * width + x] = c;
}

Color BMPCanvas::getPixel(int x, int y) {
    if (x > width || y > height)
        return Color(0, 0, 0);
    else
        return image[y * width + x];
}

void BMPCanvas::blank(bool white) {
     Color c;
     if (white)
         c = Color(255, 255, 255);
     else
         c = Color(0, 0, 0);

     int i;
     for (i = 0; i < width * height; i++)
         image[i] = c;
}

string BMPCanvas::getFileName() {
    return fileName;
}
void BMPCanvas::setFileName(string iFileName) {
    fileName = iFileName;
}
