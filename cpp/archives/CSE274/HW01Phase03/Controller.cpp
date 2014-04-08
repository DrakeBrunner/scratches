#include "Fractal.h"
#include "BMPCanvas.h"

#include <iostream>
#include <fstream>
#include <string>

void testGettersAndSetters();
void testWriteToFile();
void testRead();

int main(){

    //YOUR CODE STARTS HERE
    // helloworld();

    // std::cout << "Testing getters and setters" << std::endl;
    // testGettersAndSetters();

    // std::cout << "testing writeBMP() method" << std::endl;
    // testWriteToFile();

    // std::cout << "testing read() method" << std::endl;
    // testRead();

    // Draw a square in a 256x256 px BMP image
    MakeSquare256();

    MakeSphere256();

	std::cout << "sierpinski method" << std::endl;
    Sierpinski("Sierpinski", 1000, 1000000);

	std::cout << "starting julia part 1... (going to take some time)" << std::endl;
	Julia("Julia1", Complex(0.285, 0.01));
	std::cout << "now starting julia part 2..." << std::endl;
	Julia("Julia2", Complex(PHI - 2, PHI - 1));
	std::cout << "finally mandelbrot..." << std::endl;
	Mandelbrot("Mandelbrot");
    //YOUR CODE ENDS HERE

    return 0;
}

/*
    This method creates a new BMPCanvas object, tests the getters for that,
    sets new values to the variables, and tests if they were successfully
    changed.
*/
void testGettersAndSetters() {
    BMPCanvas b("foo", 100, 200);
    std::cout << "  testing getters" << std::endl;
	std::cout << b.getFileName() << std::endl;
	std::cout << b.getWidth() << std::endl;
	std::cout << b.getHeight() << std::endl;
    bool white = true;
    b.blank(white);
    Color c = b.getPixel(10, 10);
	std::cout << "r:" << (int)c.red << std::endl;
	std::cout << "g:" << (int)c.green << std::endl;
	std::cout << "b:" << (int)c.blue << std::endl;

    std::cout << "  testing setters" << std::endl;
    b.setFileName("barbar");
    b.setDims(300, 400);
    b.setPixel(10, 10, Color(125, 125, 125));
    std::cout << "  ...finished testing setters" << std::endl;

    std::cout << "  testing getters...again" << std::endl;
	std::cout << b.getFileName() << std::endl;
	std::cout << b.getWidth() << std::endl;
	std::cout << b.getHeight() << std::endl;
    c = b.getPixel(10, 10);
	std::cout << "r:" << (int)c.red << std::endl;
	std::cout << "g:" << (int)c.green << std::endl;
	std::cout << "b:" << (int)c.blue << std::endl;
}

/*
    This method creates a 800x800-image painted in black.
*/
void testWriteToFile() {
    BMPCanvas b("testImage", 800, 800);
    // Make the image black
    b.blank(false);
    b.writeBMP();
}

void testRead() {
    // This constructor reads in the image
    BMPCanvas b("testImage");

    Color c = b.getPixel(20, 20);
    std::cout << "r:" << (int)c.red << std::endl;
    std::cout << "g:" << (int)c.green << std::endl;
    std::cout << "b:" << (int)c.blue << std::endl;
}
