CC=gcc
CXX=g++
CXXFLAGS=-Wall -g
PROGRAM=hw02phase01
OBJS=main.o encrypt.o rsa_functions.o

all: $(PROGRAM)

$(PROGRAM): $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o $(PROGRAM)

main.o: main.cpp
	$(CXX) $(CXXFLAGS) -c main.cpp

encrypt.o: encrypt.h encrypt.cpp
	$(CXX) $(CXXFLAGS) -c encrypt.cpp

rsa_functions.o: rsa_functions.h rsa_functions.cpp
	$(CXX) $(CXXFLAGS) -c rsa_functions.cpp

clean:
	rm -f *.o *~ $(PROGRAM)
