#include <string>

#pragma once

using namespace std;

class Node {
public:
    Node(const int node_type);
    ~Node();

    int get_node_type();
    virtual string to_string();
    virtual string compile();

    static const int ROOT = 0;
    static const int NODE_ASSIGNMENT = 1;
    static const int NODE_LOOP = 2;
    static const int NODE_VALUE = 3;
    static const int NODE_OPERATOR = 4;
    static const int NODE_CONDITIONAL = 5;

protected:
    int node_type;
};

