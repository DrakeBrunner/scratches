#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
using namespace std;

int main() {
	vector<int> v;
	vector<int> v2(20);

	v.push_back(2);
	v.push_back(23);
	v.push_back(-2);
	v.push_back(32);

	copy(v.begin(), v.end(), v2.begin());

	copy(v.begin(), v.end(), ostream_iterator<int>(std::cout, " "));
	cout << endl;

	copy(v2.begin(), v2.end(), ostream_iterator<int>(std::cout, " "));
	cout << endl;

	return 0;
}
