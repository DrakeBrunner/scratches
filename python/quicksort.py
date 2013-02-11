#!/usr/bin/python

# Quick sort algorithm for python

import random

def quicksort(a):
    l = 0
    r = len(a) - 1
    lstack = []
    rstack = []

    lstack.append(l)
    rstack.append(r)
    stack = 1

    while stack > 0:
        l = lstack.pop()
        r = rstack.pop()
        stack -= 1

        # bubble sort when not long enough
        if r - l <= 4:
            for i in xrange(0, len(a) - 1):
                for j in xrange(len(a) - 1, i, -1):
                    if a[j - 1] > a[j]:
                        tmp = a[j - 1]
                        a[j - 1] = a[j]
                        a[j] = tmp
        else:
            if r - l <= 1:
                continue

            center = partition(a, l, r)

            # push to right
            if center + 1 < r:
                lstack.append(center + 1)
                rstack.append(r)
                stack += 1
            # push to left
            if l < center - 1:
                lstack.append(l)
                rstack.append(center - 1)
                stack += 1

def partition(a, l, r):
    p = pivot(a, l, r)

    # swap pivot and left
    tmp = a[p]
    a[p] = a[l]
    a[l] = tmp
    p = l

    i = l + 1
    j = r

    while True:
        while i < r and a[i] < a[p]:
            i += 1
        while j > 0 and a[j] > a[p]:
            j -= 1

        if i >= j:
            break

        # Swap i and j
        tmp = a[i]
        a[i] = a[j]
        a[j] = tmp

        i += 1
        j -= 1

    # swap pivot and center
    tmp = a[j]
    a[j] = a[p]
    a[p] = tmp

    return j

def pivot(a, l, r):
    mid = (l + r) / 2

    if a[l] > a[r]:
        return l if a[mid] > a[l] else r if a[r] > a[mid] else mid
    else:
        return r if a[mid] > a[r] else l if a[l] > a[mid] else mid

# Generate random list
a = [int(1000 * random.random()) for i in xrange(10)]
# a = [863, 52, 628, 888, 32, 303, 607, 388, 944, 91]

print "Before sort"
print a
quicksort(a)
print "After sort"
print a
