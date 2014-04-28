#include "LoopNode.h"

int LoopNode::label_number = 0;

LoopNode::LoopNode() : Node(Node::NODE_LOOP) {
}

LoopNode::LoopNode(const int loop_type) : Node(Node::NODE_LOOP) {
    this->loop_type = loop_type;
}

void LoopNode::set_condition(Node* condition) {
    this->condition = condition;
}

void LoopNode::set_body(list<Node*> body) {
    this->body = body;
}

int LoopNode::get_loop_type() {
    return loop_type;
}

string LoopNode::to_string() {
    string ret = "LOOP:\n";
    ret += "  COND: ";
    ret += condition->to_string() + "\n";
    ret += "  BODY:\n";
    list<Node*>::iterator it;
    for (it = body.begin(); it != body.end(); it++) {
        ret += "    " + (*it)->to_string() + "\n";
    }

    return ret;
}

string LoopNode::compile() {
    if (loop_type == LOOP_DO_WHILE)
        return compile_do_while();
    else if (loop_type == LOOP_WHILE)
        return compile_while();
    // TODO: for

    return "";
}

string LoopNode::compile_do_while() {
    int current_label = label_number;
    // Create label
    string ret = ".LABEL" + std::to_string(current_label) + ":\n";

    list<Node*>::iterator it;
    for (it = body.begin(); it != body.end(); it++) {
        ret += (*it)->compile() + "\n";
    }

    // Add condition
    // Operator
    if (condition->get_node_type() == NODE_OPERATOR) {
        OperatorNode* op = (OperatorNode*)condition;
        ret += op->get_branch(label_number);
    }
    // Condition is a variable
    else if (condition->get_node_type() == NODE_VALUE) {
        ret += "bnez $" + std::to_string(((ValueNode*)condition)->get_reg_number());
        ret += ", .LABEL" + std::to_string(current_label) + "\n";
    }

    label_number++;
    return ret + "\n";
}

string LoopNode::compile_while() {
    string ret = ".LABEL" + std::to_string(label_number++);

    // TODO

    return ret;
}
