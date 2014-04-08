CXX=g++
CXXFLAGS=-Wall -g
PROGRAM=hw03phase01
OBJS=IntList.o Controller.o

all: $(PROGRAM)

$(PROGRAM): $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o $(PROGRAM)

IntList.o: IntList.h IntList.cpp
	$(CXX) $(CXXFLAGS) -c IntList.cpp

Controller.o: Controller.cpp
	$(CXX) $(CXXFLAGS) -c Controller.cpp

clean:
	rm -f *.o *~ $(PROGRAM)
