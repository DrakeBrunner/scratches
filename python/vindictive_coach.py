#!/usr/bin/python

# UVA online judge
# 702 The Vindictive Coach

def init(a, N):
    """ Initializes the 2-D array with -1 """
    for i in xrange(N):
        a.append([])
        for j in xrange(N):
            a[i].append(-1)

def upward(up, down, s, t):
    """ simulates the upward move (recursive) """

    print "up\t\t", s, t, up[s][t]
    if (up[s][t] == -1):
        if (s == 0 and t == 0):
            up[s][t] = 1
        elif (s == 0 or t == 0):
            up[s][t] = 0
        else:
            up[s][t] += downward(up, down, s - 1, t)

    return up[s][t]

def downward(up, down, s, t):
    """ simulates the downward move (recursive) """

    print "down\t", s, t
    if (down[s][t] == -1):
        if (s == 0 and t == 0):
            down[s][t] = 1
        elif (s < 0 or t < 0):
            down[s][t] = 0
        else:
            down[s][t] += upward(up, down, s, t - 1)

    return down[s][t]

up = []
down = []
N = 4
m = 2
init(up, N)
init(down, N)
downward(up, down, m - 1, N - m)

print up
print down
print down[m - 1][N - m]
