CXX=g++
CXXFLAGS=-Wall -g
PROGRAM=hw03phase02
OBJS=IntList.o Deck.o Experiment.o Controller.o

all: $(PROGRAM)

$(PROGRAM): $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o $(PROGRAM)

IntList.o: IntList.h IntList.cpp
	$(CXX) $(CXXFLAGS) -c IntList.cpp

Deck.o: Deck.h Deck.cpp
	$(CXX) $(CXXFLAGS) -c Deck.cpp

Experiment.o: Experiment.h Experiment.cpp
	$(CXX) $(CXXFLAGS) -c Experiment.cpp

Controller.o: Controller.cpp
	$(CXX) $(CXXFLAGS) -c Controller.cpp

clean:
	rm -f *.o *~ $(PROGRAM)
