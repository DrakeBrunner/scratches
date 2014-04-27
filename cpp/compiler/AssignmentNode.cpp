#include "AssignmentNode.h"

AssignmentNode::AssignmentNode() : Node(Node::NODE_ASSIGNMENT) {
}

void AssignmentNode::set_left(Node* left) {
    this->left = left;
}

void AssignmentNode::set_right(Node* right) {
    this->right = right;
}

string AssignmentNode::to_string() {
    std::string ret = "";
    ret += "ASSIGNMENT: ";
    if (left != NULL)
        ret += left->to_string();
    else
        ret += "NULL";
    ret += " = ";
    if (right != NULL)
        ret += right->to_string();
    else
        ret += "NULL";

    return ret;
}
