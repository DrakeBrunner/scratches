main08-3: main08-3.o liqueue.o node.o
	$(CC) -o main08-3 main08-3.o liqueue.o node.o
main08-3.o: main08-3.c liqueue.h
	$(CC) -c main08-3.c
liqueue.o: liqueue.c liqueue.h node.h
	$(CC) -c liqueue.c
node.o: node.c node.h
	$(CC) -c node.c
