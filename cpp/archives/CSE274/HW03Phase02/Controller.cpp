#include <iostream>
#include <ctime>
#include <cstdlib>
#include "IntList.h"
#include "Experiment.h"

#include "Deck.h"

/*
 * Naive function that prints nodes. This function assumes that s is before e
 * and that they belong to the same list.
 */
void printList(IntListNode * s, IntListNode * e) {
    std::cout << "[ ";
    while (s != e->next) {
        std::cout << s->data << " ";
        s = s->next;
    }
    std::cout << "]" << std::endl;
}

void testCut() {
    Deck d;
    IntList * A = new IntList();
    IntList * B = new IntList();

    d.cut(A, B);

    printList(A->head(), A->tail());
    printList(B->head(), B->tail());

    delete A;
    delete B;
}

void testShuffle() {
    Deck d;
    std::cout << "Before shuffle:" << std::endl;
    // listhead->prev is sentinel so prev->prev is probably tail
    printList(d.getListHead(), d.getListHead()->prev->prev);

    d.shuffle();

    std::cout << "After shuffle:" << std::endl;
    printList(d.getListHead(), d.getListHead()->prev->prev);
}

int main() {
    srand(time(NULL));

    // testCut();
    // testShuffle();
    // doOneExperiment(7);
    doExperimentRun();
    return 0;
}
