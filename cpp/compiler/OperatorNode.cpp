#include "OperatorNode.h"

OperatorNode::OperatorNode() : Node(Node::NODE_OPERATOR) {
}

OperatorNode::OperatorNode(const int operator_type) : Node(Node::NODE_OPERATOR) {
    this->operator_type = operator_type;
}

void OperatorNode::set_left(Node* left) {
    this->left = left;
}

void OperatorNode::set_right(Node* right) {
    this->right = right;
}

string OperatorNode::to_string() {
    string ret = "";
    ret += left->to_string();
    ret += " ";
    switch (operator_type) {
        case OPERATOR_PLUS:
            ret += "+";
            break;
        case OPERATOR_MINUS:
            ret += "-";
            break;
        case OPERATOR_EQUAL:
            ret += "==";
            break;
        case OPERATOR_INEQUAL:
            ret += "!=";
            break;
        case OPERATOR_GT:
            ret += ">";
            break;
        case OPERATOR_GTE:
            ret += ">=";
            break;
        case OPERATOR_LT:
            ret += "<";
            break;
        case OPERATOR_LTE:
            ret += "<=";
            break;
    }
    ret += " ";
    ret += right->to_string();

    return ret;
}
