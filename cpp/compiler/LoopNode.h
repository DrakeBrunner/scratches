#include <list>

#include "Node.h"
#include "ValueNode.h"
#include "OperatorNode.h"

class LoopNode : public Node {
public:
    LoopNode();
    LoopNode(const int loop_type);

    void set_condition(Node* condition);
    void set_body(list<Node*> body);
    int get_loop_type();
    string to_string();
    string compile();

    static const int LOOP_FOR = 0;
    static const int LOOP_WHILE = 1;
    static const int LOOP_DO_WHILE = 2;

    static int label_number;

private:
    string compile_do_while();
    string compile_while();

    int loop_type;
    Node* condition;
    list<Node*> body;
};
