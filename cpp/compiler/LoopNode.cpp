#include "LoopNode.h"

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
    return "TODO!";
}
