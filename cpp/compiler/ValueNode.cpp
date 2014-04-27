#include "ValueNode.h"

ValueNode::ValueNode(int val) : Node(Node::NODE_VALUE) {
    this->var_name = "";
    this->value = val;
}

ValueNode::ValueNode(string var_name) : Node(Node::NODE_VALUE) {
    this->var_name = var_name;
    this->value = -1;
}

string ValueNode::get_var_name() {
    return var_name;
}

string ValueNode::to_string() {
    if (var_name == "")
        return std::to_string(value);
    else
        return var_name;
}
