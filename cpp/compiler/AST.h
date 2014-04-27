#include <iostream>
#include <list>
#include <cstdlib>
#include <string>

#include "tinyxml2.h"

#include "Node.h"
#include "AssignmentNode.h"
#include "LoopNode.h"
#include "OperatorNode.h"
#include "ValueNode.h"

// #define DEBUG

using namespace std;

class AST : public Node {
public:
    AST();
    AST(string filename);

    void parse_xml(string filename);
    string to_string();

private:
    /**
     * Iterate through nodes and add the statements to the list.
     */
    list<Node*> process_node(tinyxml2::XMLNode* node);
    AssignmentNode* do_assignment(tinyxml2::XMLElement* setq);
    OperatorNode* do_operator(tinyxml2::XMLElement* op);

    list<Node*> statements;
};
