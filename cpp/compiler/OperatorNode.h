#include "Node.h"

class OperatorNode : public Node {
public:
    OperatorNode();
    OperatorNode(const int operator_type);

    void set_left(Node* left);
    void set_right(Node* right);
    int get_operator_type();
    string to_string();
    string compile(int reg_result);

    static const int OPERATOR_PLUS = 0;
    static const int OPERATOR_MINUS = 1;
    static const int OPERATOR_EQUAL = 2;
    static const int OPERATOR_INEQUAL = 3;
    static const int OPERATOR_GT = 4;
    static const int OPERATOR_GTE = 5;
    static const int OPERATOR_LT = 6;
    static const int OPERATOR_LTE = 7;

private:
    int operator_type;
    Node* left;
    Node* right;
};

