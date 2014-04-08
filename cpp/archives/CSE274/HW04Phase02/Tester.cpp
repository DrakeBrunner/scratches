#include <iostream>
#include <string>
#include "Hash.h"
#include "Trends.h"
#include "MyTrends.h"

void testHash() {
    Hash h(10, 3, 5);
    h.set("barbar", 5);
    h.set("bazbar", 3);

    std::cout << h.get("barbar") << std::endl;
    std::cout << h.get("bazbar") << std::endl;

    h.del("bazbar");

    std::cout << h.get("barbar") << std::endl;
    std::cout << h.get("bazbar") << std::endl;

    std::cout << h.defined("barbar") << std::endl;
    std::cout << h.defined("bazbar") << std::endl;
    std::cout << h.defined("foobar") << std::endl;
}

// {{{
void testMyTrendsIncreaseCountPrint(MyTrends & mt) {
    for (int i = 0; i < mt.numEntries(); i++) {
        std::string s = mt.getNthPopular(i);
        std::cout << s << ": " << mt.getCount(s)<< std::endl;
    }
}

void testMyTrendsIncreaseCountBasic() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("a", 2);
    mt.increaseCount("ab", 1);
    mt.increaseCount("abc", 3);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountDuplicate() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("foo", 3);
    mt.increaseCount("foo", 4);
    mt.increaseCount("foo", 2);
    mt.increaseCount("foo", 1);
    mt.increaseCount("foo", 5);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountAddToTop() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("a", 2);
    mt.increaseCount("b", 3);
    mt.increaseCount("c", 4);
    mt.increaseCount("d", 5);
    mt.increaseCount("e", 6);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountAddToBottom() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("e", 6);
    mt.increaseCount("d", 5);
    mt.increaseCount("c", 4);
    mt.increaseCount("b", 3);
    mt.increaseCount("a", 2);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountAddAlternately() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("a", 2);
    mt.increaseCount("e", 6);
    mt.increaseCount("b", 3);
    mt.increaseCount("d", 5);
    mt.increaseCount("c", 4);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountAddAlternatelyDuplicate() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("a", 2);
    mt.increaseCount("e", 6);
    mt.increaseCount("b", 3);
    mt.increaseCount("e", 9);
    mt.increaseCount("a", 1);
    mt.increaseCount("d", 5);
    mt.increaseCount("c", 4);
    mt.increaseCount("c", 2);
    mt.increaseCount("c", 3);
    mt.increaseCount("c", 1);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountAlphabetical() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("a", 2);
    mt.increaseCount("ab", 2);
    mt.increaseCount("ac", 2);
    mt.increaseCount("acd", 2);
    mt.increaseCount("acda", 2);
    mt.increaseCount("acdb", 2);
    mt.increaseCount("ae", 2);
    mt.increaseCount("afoo", 2);

    testMyTrendsIncreaseCountPrint(mt);
}

void testMyTrendsIncreaseCountNonAlphabet() {
    MyTrends mt(100, 2, 5);

    mt.increaseCount("1", 2);
    mt.increaseCount(",", 2);
    mt.increaseCount(".", 2);
    mt.increaseCount("you're", 2);
    mt.increaseCount("foo!baz", 2);
    mt.increaseCount("$code =~ s/bug/feature/g", 2);
    mt.increaseCount("\"hey there\"", 2);
    mt.increaseCount(" ", 2);

    mt.increaseCount("1", 1);
    mt.increaseCount(",", 2);
    mt.increaseCount(".", 3);
    mt.increaseCount("you're", 4);
    mt.increaseCount("foo!baz", 5);
    mt.increaseCount("$code =~ s/bug/feature/g", 6);
    mt.increaseCount("\"hey there\"", 7);
    mt.increaseCount(" ", 8);

    testMyTrendsIncreaseCountPrint(mt);
}

// }}}

void testMyTrends() {
    std::cout << "Basic" << std::endl;
    testMyTrendsIncreaseCountBasic();

    std::cout << "Duplicate" << std::endl;
    testMyTrendsIncreaseCountDuplicate();

    std::cout << "Add to top" << std::endl;
    testMyTrendsIncreaseCountAddToTop();

    std::cout << "Add to bottom" << std::endl;
    testMyTrendsIncreaseCountAddToBottom();

    std::cout << "Add alternately" << std::endl;
    testMyTrendsIncreaseCountAddAlternately();

    std::cout << "Add alternately with duplicate" << std::endl;
    testMyTrendsIncreaseCountAddAlternatelyDuplicate();

    std::cout << "Add alphabetically" << std::endl;
    testMyTrendsIncreaseCountAlphabetical();

    std::cout << "Non-alphabet characters" << std::endl;
    testMyTrendsIncreaseCountNonAlphabet();
}

void testTrends() {
    Trends * t = new MyTrends(10, 5, 2);

    t->increaseCount("the", 1);
    t->increaseCount("baa", 1);
    t->increaseCount("baz", 1);
    t->increaseCount("the", 1);
    t->increaseCount("foo", 1);
    t->increaseCount("hello", 1);
    t->increaseCount("hello", 1);

    for (int i = 0; i < t->numEntries(); i++) {
        std::string s = t->getNthPopular(i);
        std::cout << s << t->getCount(s) << std::endl;
    }
}

int main() {
    // testHash();
    testMyTrends();
    // testTrends();
    return 0;
}

/* vim: foldmethod=marker */
