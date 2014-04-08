#include <iostream>
#include <cassert>
#include <cstdlib>
#include "Deck.h"

/*
 * 0-12 correspond to the ace-king of hearts,
 * 13-25 are the ace-king of diamonds,
 * 26-38 are the ace-king of clubs,
 * 39-51 are the ace-king of spades
 */
Deck::Deck() {
    int i;
    for (i = 0; i < 52; i++)
        deckList.push_back(i);
}

void Deck::shuffle() {
    IntList * A = new IntList();
    IntList * B = new IntList();
    cut(A, B);

    // Start by taking 0 to 2 cards from A, then B, then A...
    IntList * list_to_use = A;
    while (!A->isEmpty() || !B->isEmpty()) {
        int random_num = rand() % 3;

        // Change to the other list
        if (random_num == 0 || list_to_use->isEmpty()) {
            list_to_use = (list_to_use == A) ? B : A;
            continue;
        }

        // This counts as "1 card"
        IntListNode * start = list_to_use->tail();
        IntListNode * end = list_to_use->tail();

        // For the rest of the cards
        for (int i = 0; i < random_num - 1; i++) {
            // Stop when list_to_use has gone all the way to the front
            if (start->prev == list_to_use->getSentinel())
                break;

            start = start->prev;
        }

        // Put to top of the deck
        splice(start, end, deckList.getSentinel());
        // Put to bottom of the deck
        // splice(start, end, deckList.getSentinel()->prev);

#ifdef DEBUG
        std::cout << ((list_to_use == A) ? "A" : "B") << " ";
        if (start->data == end->data)
            std::cout << start->data << std::endl;
        else
            std::cout << start->data << ", " << end->data << std::endl;
#endif

        // Switch to the other list
        list_to_use = (list_to_use == A) ? B : A;
    }
}

IntListNode * Deck::getListHead() {
    return deckList.head();
}

void Deck::cut(IntList * A, IntList * B) {
    assert(A != NULL && B != NULL);

    IntListNode * sourceStartForA = deckList.head();
    IntListNode * sourceEndForA = deckList.head();


    int i;
    // Has to shift 25 times (in order to get to the 26th element)
    for (i = 0; i < 25; i++)
        sourceEndForA = sourceEndForA->next;

    IntListNode * sourceStartForB = sourceEndForA->next;
    IntListNode * sourceEndForB = deckList.tail();

    splice(sourceStartForA, sourceEndForA, A->getSentinel());
    splice(sourceStartForB, sourceEndForB, B->getSentinel());
}
