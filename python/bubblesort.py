#!/usr/bin/python

# Bubble sort algorithm

import random

def bubblesort(a):
    for i in xrange(0, len(a) - 1):
        for j in xrange(len(a) - 1, i, -1):
            if a[j - 1] > a[j]:
                tmp = a[j - 1]
                a[j - 1] = a[j]
                a[j] = tmp


# Generate random list
#a = [int(1000 * random.random()) for i in xrange(10)]
a = [863, 52, 628, 888, 32, 303, 607, 388, 944, 91]

print "Before sort"
print a
bubblesort(a)
print "After sort"
print a
