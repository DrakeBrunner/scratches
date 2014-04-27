#include "Node.h"

class AssignmentNode : public Node {
public:
    AssignmentNode();

    void set_left(Node* left);
    void set_right(Node* right);
    string to_string();

private:
    Node* left;
    Node* right;
};

