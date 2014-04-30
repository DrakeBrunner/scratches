#include "AssignmentNode.h"
#include "OperatorNode.h"
#include "ValueNode.h"

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

    ret += "\n";
    return ret;
}

string AssignmentNode::compile() {
    string ret = "";
    // Calculate operators first if RHS uses operator
    // Use $t0 (= $8)
    int rhs_result = 8;
    if (right->get_node_type() == NODE_OPERATOR) {
        ret += ((OperatorNode*)right)->compile(rhs_result);
    }

    ret += "add $";
    int reg_assign = ((ValueNode*)left)->get_reg_number();

    ret += std::to_string(reg_assign) + ", ";

    switch (right->get_node_type()) {
        case NODE_VALUE:
            ret += "$0, ";
            // add $s0, $zero, $s1
            if (((ValueNode*)right)->is_var())
                ret += "$" + std::to_string(((ValueNode*)right)->get_reg_number());
            // add $s0, $zero, 5
            else
                ret += std::to_string(((ValueNode*)right)->get_value());
            break;
        case NODE_OPERATOR:
            // Add previously calculated result
            ret += "$0, $" + std::to_string(rhs_result);
            break;
    }

    return ret;
}
