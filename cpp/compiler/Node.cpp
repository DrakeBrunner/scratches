#include "Node.h"

Node::Node(const int node_type) {
    this->node_type = node_type;
}

Node::~Node() {
}

int Node::get_node_type() {
    return node_type;
}

string Node::to_string() {
    string ret;
    switch (node_type) {
        case NODE_ASSIGNMENT:
            ret = "Assignment";
            break;
        case NODE_LOOP:
            ret = "Loop";
            break;
        case NODE_VALUE:
            ret = "Value";
            break;
        case NODE_OPERATOR:
            ret = "Operator";
            break;
    }
    return ret;
}

string Node::compile() {
    return "";
}
