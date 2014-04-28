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
#define VAR_REG 16

using namespace std;

class AST : public Node {
public:
    AST(string filename);

    void parse_xml(string filename);
    string to_string();
    string compile();

private:
    /**
     * Iterate through nodes and add the statements to the list.
     */
    list<Node*> process_node(tinyxml2::XMLNode* node);
    AssignmentNode* do_assignment(tinyxml2::XMLElement* setq);
    OperatorNode* do_operator(tinyxml2::XMLElement* op);

    int reg_number(string var_name, int start = 0, int end = 32);

    list<Node*> statements;
    /**
     * Store variables and their corresponding registers.
     */
    string* registers;

};
