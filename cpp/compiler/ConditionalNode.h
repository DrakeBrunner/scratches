#include <list>

#include "Node.h"
#include "ValueNode.h"
#include "OperatorNode.h"

class ConditionalNode : public Node {
public:
    ConditionalNode();

    void set_condition(Node* condition);
    void set_body_true(list<Node*> body);
    void set_body_false(list<Node*> body);
    string to_string();
    string compile();

    static int label_number;

private:
    Node* condition;
    list<Node*> body_true;
    list<Node*> body_false;
};
