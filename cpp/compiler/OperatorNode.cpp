#include "OperatorNode.h"
#include "ValueNode.h"

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

string OperatorNode::compile(int reg_result) {
    string ret = "";
    int tmp_result = reg_result + 1;

    // Left Operand
    // Calculate operators first if left operand is an operation
    if (left->get_node_type() == NODE_OPERATOR) {
        tmp_result++;
        ret += ((OperatorNode*)left)->compile(tmp_result);
    }

    ret += "add $" + std::to_string(tmp_result) + ", ";
    switch (left->get_node_type()) {
        case NODE_VALUE:
            ret += "$0, ";
            // add $t0, $zero, $t1
            if (((ValueNode*)left)->is_var())
                ret += "$" + std::to_string(((ValueNode*)left)->get_reg_number());
            // add $t0, $zero, 5
            else
                ret += std::to_string(((ValueNode*)left)->get_value());
            break;
        case NODE_OPERATOR:
            // Add previously calculated result
            ret += "$" + std::to_string(tmp_result) + ", $" + std::to_string(tmp_result - 1);
            break;
    }
    ret += "\n";


    // Right Operand
    // Calculate operators first if right operand is an operation
    if (right->get_node_type() == NODE_OPERATOR) {
        tmp_result++;
        ret += ((OperatorNode*)right)->compile(tmp_result);
    }

    ret += "add $" + std::to_string(reg_result) + ", ";

    switch (right->get_node_type()) {
        case NODE_VALUE:
            ret += "$" + std::to_string(tmp_result) + ", ";
            // add $t0, $t1, $s1
            if (((ValueNode*)right)->is_var())
                ret += "$" + std::to_string(((ValueNode*)right)->get_reg_number());
            // add $t0, $t1, 5
            else {
                // If it's subtracting
                if (operator_type == OPERATOR_MINUS)
                    ret += "-";
                ret += std::to_string(((ValueNode*)right)->get_value());
            }
            break;
        case NODE_OPERATOR:
            // Add previously calculated result
            ret += "$" + std::to_string(tmp_result) + ", $" + std::to_string(tmp_result - 1);
            break;
    }
    ret += "\n";

    return ret;
}

string OperatorNode::get_branch(string label_name, int label_number, bool negate) {
    string ret;

    int tmp_result = 8;
    int left_operand = tmp_result + 1;
    int right_operand = tmp_result + 2;
    // Get left operand
    if (left->get_node_type() == NODE_OPERATOR)
        ret += compile(left_operand);

    // Get right operand
    if (right->get_node_type() == NODE_OPERATOR)
        ret += compile(right_operand);

    if (operator_type == OPERATOR_EQUAL)
        ret += negate ? "bne" : "beq";
    else if (operator_type == OPERATOR_INEQUAL)
        ret += negate ? "beq" : "bne";
    else if (operator_type == OPERATOR_GT)
        ret += negate ? "ble" : "bgt";
    else if (operator_type == OPERATOR_GTE)
        ret += negate ? "blt" : "bge";
    else if (operator_type == OPERATOR_LT)
        ret += negate ? "bge" : "blt";
    else if (operator_type == OPERATOR_LTE)
        ret += negate ? "bgt" : "ble";

    ret += " ";

    // Left operand
    // If operator, use the register previously calculated
    if (left->get_node_type() == NODE_OPERATOR)
        ret += "$" + std::to_string(left_operand) + ", ";
    // Variable (no constant)
    else
        ret += "$" + std::to_string(((ValueNode*)left)->get_reg_number()) + ", ";


    // Right operand
    // If operator, use the register previously calculated
    if (left->get_node_type() == NODE_OPERATOR)
        ret += "$" + std::to_string(right_operand) + ", ";
    // Value
    else {
        ValueNode* val = (ValueNode*)right;
        // If it's a variable
        if (val->is_var())
            ret += "$" + std::to_string(val->get_reg_number()) + ", ";
        // Constant
        else
            ret += std::to_string(val->get_value()) + ", ";
    }

    // Add where to jump
    ret += label_name + std::to_string(label_number);

    return ret + "\n";
}
