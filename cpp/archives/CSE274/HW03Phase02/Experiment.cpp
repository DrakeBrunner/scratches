/*****
 * Author   : brinkmwj
 * Date     : 2009-07-23
 * Sources  : All code is original
 * Purpose  : I have implemented the chiSquared function for you, do not change it.
 * Note     : N/A
 *****/

#include "Experiment.h"
#include <fstream>
#include <iomanip>
#include "Deck.h"
#include <sstream>
#include <iostream>
#include <cstring>

#define MAXNUMSHUFFLES 15

using namespace std;

/*
 * DO NOT CHANGE THIS FUNCTION! Add all your code after this!
 */ 
double chiSquared(long* rowHead, int rowLength){
    long rowTotal = 0;
    double expected = 0.0;
    double chiSq = 0.0;
    for(int i=0;i<rowLength; i++){
        rowTotal += rowHead[i];
    }
    expected = ((double)rowTotal)/((double)rowLength);

    for(int i=0; i<rowLength; i++){
        chiSq = chiSq + (((rowHead[i] - expected)*(rowHead[i] - expected))/expected);
    }

    return chiSq;
}

/*
 * Add your doOneExperiment here
 */

double doOneExperiment(int numshuffles) {
    long * arr = new long[52 * 52];
    // Initialize array
    for (int pos = 0; pos < 52; pos++)
        for (int card = 0; card < 52; card++)
            arr[pos * 52 + card] = 0;

    for (int experiment = 0; experiment < NUMEXPERIMENTS; experiment++) {
        Deck d;
        // Shuffle numshuffles times
        for (int i = 0; i < numshuffles; i++)
            d.shuffle();

        // Go through the deck
        IntListNode * node = d.getListHead();
        for (int pos = 0; pos < 52; pos++) {
            arr[pos * 52 + node->data]++;
            node = node->next;
        }
    }

    // Print to file
    char * buf = new char[15];
    std::sprintf(buf, "table%d.txt", numshuffles);
    std::ofstream file(buf, std::ios::out);

    for (int i = 0; i < 52; i++) {
        for (int j = 0; j < 52; j++) {
            std::sprintf(buf, "%5ld ", arr[i * 52 + j]);
            file.write(buf, strlen(buf));
        }
        file.write("\n", 1);
    }
    file.close();
    delete[] buf;

    double val = chiSquared(arr, 52);
    std::cout << numshuffles << " shuffles was ";
    std::cout << (val < CRITICALVALUE ? "fair" : "biased");
    std::cout << " with chi^2 = " << val << std::endl;

    return val;
}

/*
 * Add your doExperimentRun here
 */

void doExperimentRun() {
    for (int numshuffles = 0; numshuffles < MAXNUMSHUFFLES; numshuffles++) {
        if (doOneExperiment(numshuffles) < CRITICALVALUE)
            break;
    }
}
