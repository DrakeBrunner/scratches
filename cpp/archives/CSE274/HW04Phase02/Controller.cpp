/*****
 * Author   : brinkmwj and karroje
 * Date     : 2009-10-04, 2011-03-10
 * Sources  : All code is original
 * Purpose  : The purpose of this file is to do some VERY rudimentary timing of your increaseCount
 *            method. For the QUALITY measures I am also going to test getCount and getNthPopulat, 
 *            so you might want to design some way to test out the running time of your getCount as well!
 */
#include <iostream>
#include <fstream>
#include <vector>
// #include <windows.h>
// #include <mmsystem.h>
#include <ctime>
#include <algorithm>

#include "MyTrends.h"


// #pragma comment (lib, "winmm.lib") 

/*
 * The following two functions are for the purpose of removing punctuation from either end of the
 * word and making all letters lower-case, allowing us to count the words "Here", "here", and "herE."
 * all as the same word in terms of trends.
 */
bool nonAscii(char c) {
  return !(('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'));
}

string reduceWord(string& s) {
  string::iterator last = remove_if(s.begin(), s.end(), nonAscii);
  transform(s.begin(), last, s.begin(), ::tolower);
  return s.substr(0, last-s.begin());
}


/*
 * processFile should:
 *  1) Create a new instance of your MyTrends class
 *  2) Read in the file whose words you want to count: The file name is passed in as fname
 *  3) Add all words to the Trends data structure, and calculate the time per call to addtoTrends
 *  4) Use getNthPopular and getCount to print out the total results in fname + ".out"
 */
int processFile(const char* fname){
	Trends* tr = new MyTrends();

	// First, read in the file
	ifstream in(fname);
	string s;
	vector<string> wordlist;

	while(in >> s){
	  // s = reduceWord(s);
	  if (s.size() > 0)
	    wordlist.push_back(s);
	}

	// We only want to time how long addToTrends takes, so we get
	// the starting time, which is the clock time, in milliseconds
    std::clock_t start, end;
	start = std::clock();
	// int start = timeGetTime();

	// Now add all the words to the Trends data structure
	for(unsigned int i = 0; i < wordlist.size(); i++){
		tr->increaseCount(wordlist[i], 1);
	}

	// Now get the end time
	// int end = timeGetTime();
	end = std::clock();
    double millisec = (end - start) / ((double)CLOCKS_PER_SEC / 1000);
	std::cout << "Time: " << ((1000.0 * millisec) / wordlist.size()) << " microseconds per word" << endl;

	// Now we will print out the complete results. This could be REALLY slow, if
	// your getNthPopular is not a little bit smart.
	int p = ((string)fname).rfind('.');
	string outfname = ((string)fname).substr(0, p) + ".out";
	ofstream out(outfname.c_str());
	for(int i=0; i< tr->numEntries(); i++){
		string s = tr->getNthPopular(i);
		out << tr->getCount(s) << ": " << s << endl;
	}
	out.close();

	delete tr;

	return end - start;
}

/*
 * The only purpose of main() is to call processFile with increasingingly larger and larger
 * files. A larger file will give a more accurate sense of how fast addToTrends is, but at some
 * point it may take so long to do the getNthPopular, that we aren't willing to wait for it to finish.
 */
int main(int argc, char** argv){
	/* These files are books from project Gutenberg. I have provided the inputs, as well as my outputs
	 * in the starter files */
	
	/* NOTE: You may want to comment some of these out!
	 * Unless your program is very speedy on all operations some of these will never finish.
	 */
	//processFile("examples/test.txt");
	//processFile("examples/28885.txt");
	processFile("examples/46.txt");
	//processFile("examples/23684.txt");
	//processFile("examples/1342.txt");
	//processFile("examples/6130.txt");
	//processFile("examples/4300.txt");
	//processFile("examples/3090.txt");

	return 0;
}
