#include "ValueNode.h"

ValueNode::ValueNode(int val) : Node(Node::NODE_VALUE) {
    this->var_name = "";
    this->value = val;
}

ValueNode::ValueNode(string var_name, int reg_number) : Node(Node::NODE_VALUE) {
    this->var_name = var_name;
    this->reg_number = reg_number;
    this->value = -1;
}

bool ValueNode::is_var() {
    return var_name != "";
}

string ValueNode::get_var_name() {
    return var_name;
}

int ValueNode::get_reg_number() {
    return reg_number;
}

int ValueNode::get_value() {
    return value;
}

string ValueNode::to_string() {
    if (var_name == "")
        return std::to_string(value);
    else
        return var_name + " ($" + std::to_string(reg_number) + ")";
}
