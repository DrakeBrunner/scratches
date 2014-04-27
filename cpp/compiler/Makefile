CXX=g++
CXXFLAGS=-Wall -g -std=c++11
PROGRAM=my_compiler
OBJS=tinyxml2.o Node.o AssignmentNode.o LoopNode.o ValueNode.o OperatorNode.o AST.o

all: $(PROGRAM)
	
$(PROGRAM): $(OBJS) Controller.cpp
	$(CXX) $(CXXFLAGS) -o $(PROGRAM) $(OBJS) Controller.cpp

clean:
	rm -f *.o

tinyxml2.o: tinyxml2.h tinyxml2.cpp
	$(CXX) $(CXXFLAGS) -c tinyxml2.cpp

AST.o: AST.h AST.cpp
	$(CXX) $(CXXFLAGS) -c AST.cpp

Node.o: Node.h Node.cpp
	$(CXX) $(CXXFLAGS) -c Node.cpp

AssignmentNode.o: Node.o AssignmentNode.h AssignmentNode.cpp
	$(CXX) $(CXXFLAGS) -c AssignmentNode.cpp

ValueNode.o: Node.o ValueNode.h ValueNode.cpp
	$(CXX) $(CXXFLAGS) -c ValueNode.cpp

LoopNode.o: Node.o LoopNode.h LoopNode.cpp
	$(CXX) $(CXXFLAGS) -c LoopNode.cpp

OperatorNode.o: Node.o OperatorNode.h OperatorNode.cpp
	$(CXX) $(CXXFLAGS) -c OperatorNode.cpp
