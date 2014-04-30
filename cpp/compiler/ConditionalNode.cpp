#include "ConditionalNode.h"

int ConditionalNode::label_number = 0;

ConditionalNode::ConditionalNode() : Node(Node::NODE_CONDITIONAL) {
}

void ConditionalNode::set_condition(Node* condition) {
    this->condition = condition;
}

void ConditionalNode::set_body_true(list<Node*> body) {
    this->body_true = body;
}

void ConditionalNode::set_body_false(list<Node*> body) {
    this->body_false = body;
}

string ConditionalNode::to_string() {
    string ret = "IF: ";
    ret += condition->to_string() + "\n";
    list<Node*>::iterator it;
    for (it = body_true.begin(); it != body_true.end(); it++) {
        ret += "  " + (*it)->to_string() + "\n";
    }

    ret += "ELSE:\n";
    for (it = body_false.begin(); it != body_false.end(); it++) {
        ret += "  " + (*it)->to_string() + "\n";
    }

    return ret;
}

string ConditionalNode::compile() {
    string ret = "";

    int current_label = label_number;
    // Add condition
    // Operator
    if (condition->get_node_type() == NODE_OPERATOR) {
        OperatorNode* op = (OperatorNode*)condition;
        // Get negated branch
        ret += op->get_branch(".COND", current_label, true);
    }
    // Condition is a variable
    else if (condition->get_node_type() == NODE_VALUE) {
        ret += "beqz $" + std::to_string(((ValueNode*)condition)->get_reg_number());
        ret += ", .COND" + std::to_string(current_label) + "\n";
    }

    // Add true condition body to "fall through"
    list<Node*>::iterator it;
    for (it = body_true.begin(); it != body_true.end(); it++) {
        ret += (*it)->compile() + "\n";
    }
    // Skip false body
    ret += "j .COND" + std::to_string(current_label + 1) + "\n";

    // Create label for false condition to jump
    ret += ".COND" + std::to_string(current_label) + ":\n";

    // Add false body
    for (it = body_false.begin(); it != body_false.end(); it++) {
        ret += (*it)->compile() + "\n";
    }

    // Create label for true condition to jump (in order to skip false body)
    ret += ".COND" + std::to_string(current_label + 1) + ":\n";

    // Uses 2 label per conditional
    label_number += 2;
    return ret;
}
