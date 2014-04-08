CXX=g++
CXXFLAGS=-Wall -O5
PROGRAM=hw04phase02
OBJS=Hash.o KeyList.o MyTrends.o Controller.o

all: $(PROGRAM)

$(PROGRAM): $(OBJS)
	$(CXX)  -o $(PROGRAM) $(CXXFLAGS) $(OBJS)

Tester.o: Tester.cpp
	$(CXX) $(CXXFLAGS) -c Tester.cpp

Hash.o: Hash.h Hash.cpp
	$(CXX) $(CXXFLAGS) -c Hash.cpp

MyTrends.o: Trends.h MyTrends.h MyTrends.cpp
	$(CXX) $(CXXFLAGS) -c MyTrends.cpp

KeyList.o: KeyList.h KeyList.cpp
	$(CXX) $(CXXFLAGS) -c KeyList.cpp

Controller.o: Controller.cpp
	$(CXX) $(CXXFLAGS) -c Controller.cpp

clean:
	rm -f *.o
