#include "AST.h"

AST::AST() : Node(Node::ROOT) {
}

AST::AST(string filename) : Node(Node::ROOT) {
    parse_xml(filename);
}

void AST::parse_xml(string filename) {
    using namespace tinyxml2;
    XMLDocument doc;
    doc.LoadFile(filename.c_str());
    XMLNode* code = doc.FirstChildElement("code");

    statements = process_node(code->FirstChild());
}

list<Node*> AST::process_node(tinyxml2::XMLNode* node) {
    list<Node*> statements;

    while (node != NULL && node->ToElement() != NULL) {
        std::string node_type(node->Value());

        // Assignments
        if (node_type == "setq") {
            AssignmentNode* an = do_assignment(node->ToElement());
            statements.push_back(an);
#ifdef DEBUG
            std::cout << an->to_string() << std::endl;
#endif
        }
        else if (node_type == "loop") {
            LoopNode* loop;
            if (node->ToElement()->Attribute("type", "for"))
                loop = new LoopNode(LoopNode::LOOP_FOR);
            else if (node->ToElement()->Attribute("type", "while"))
                loop = new LoopNode(LoopNode::LOOP_WHILE);
            else if (node->ToElement()->Attribute("type", "do-while"))
                loop = new LoopNode(LoopNode::LOOP_DO_WHILE);
            // Invalid loop type
            else {
                std::string loop_type = node->ToElement()->Attribute("type");
                std::cerr << "Invalid loop type: " << loop_type << std::endl;
                exit(1);
            }

            // Get condition
            tinyxml2::XMLElement* cond = node->FirstChildElement("cond");
            // Condition is an operation (e.g. +, -, <)
            if (cond->FirstChildElement("o") != NULL) {
                loop->set_condition(do_operator(cond->FirstChildElement("o")));
            }
            // Condition is a variable
            else if (cond->FirstChildElement("v") != NULL) {
                // Set it to variable
                loop->set_condition(new ValueNode(cond->FirstChild()->Value()));
            }
            // Condition is a constant
            else if (cond->FirstChildElement("c") != NULL) {
                // Set it to variable (has to be int)
                std::string val(cond->FirstChild()->Value());
                int i;
                // Parse int
                sscanf(val.c_str(), "%d", &i);
                loop->set_condition(new ValueNode(i));
            }

            // Get body (and process)
            loop->set_body(process_node(node->FirstChildElement("body")->FirstChild()));

#ifdef DEBUG
            std::cout << loop->to_string() << std::endl;
#endif

            // Add loop to statements
            statements.push_back(loop);
        }

        node = node->NextSibling();
    }

    return statements;
}

AssignmentNode* AST::do_assignment(tinyxml2::XMLElement* setq) { /* {{{ */
    using namespace tinyxml2;

    AssignmentNode* ret = new AssignmentNode();

    // Has to be assigned to a variable
    XMLElement* operand = setq->FirstChildElement("v");
    if (left == NULL) {
        std::cerr << "Not an assignment to variable" << std::endl;
        exit(1);
    }

    // Set left operand
    // Get variable name
    std::string var_name(operand->FirstChild()->Value());
    ret->set_left(new ValueNode(var_name));

    // Set right operand
    // Focus on right operand
    operand = operand->NextSiblingElement();
    // See what kind of operand it is (another operation, const, var?)
    std::string operand_type(operand->Value());
    // Right operand is an operation (e.g. +, -, <)
    if (operand_type == "o") {
        ret->set_right(do_operator(operand));
    }
    // Right operand is a variable
    else if (operand_type == "v") {
        // Set it to variable
        ret->set_right(new ValueNode(operand->FirstChild()->Value()));
    }
    // Right oprand is a constant
    else if (operand_type == "c") {
        // Set it to variable (has to be int)
        std::string val(operand->FirstChild()->Value());
        int i;
        // Parse int
        sscanf(val.c_str(), "%d", &i);
        ret->set_right(new ValueNode(i));
    }

    return ret;
}   /* }}} */

OperatorNode* AST::do_operator(tinyxml2::XMLElement* operand) { /* {{{ */
    using namespace tinyxml2;

    std::string operator_type(operand->FirstChild()->Value());

    OperatorNode* ret;
    if (operator_type == "+")
        ret = new OperatorNode(OperatorNode::OPERATOR_PLUS);
    else if (operator_type == "-")
        ret = new OperatorNode(OperatorNode::OPERATOR_MINUS);
    else if (operator_type == "==")
        ret = new OperatorNode(OperatorNode::OPERATOR_EQUAL);
    else if (operator_type == "!=")
        ret = new OperatorNode(OperatorNode::OPERATOR_INEQUAL);
    else if (operator_type == ">")
        ret = new OperatorNode(OperatorNode::OPERATOR_GT);
    else if (operator_type == ">=")
        ret = new OperatorNode(OperatorNode::OPERATOR_GTE);
    else if (operator_type == "<")
        ret = new OperatorNode(OperatorNode::OPERATOR_LT);
    else if (operator_type == "<=")
        ret = new OperatorNode(OperatorNode::OPERATOR_LTE);

    // Add left operand
    // Focus on left operand
    operand = operand->NextSiblingElement();
    std::string operand_type(operand->Value());
    // TODO: repeating code
    // Left operand is an operation (e.g. +, -, <)
    if (operand_type == "o") {
        // Recursive call
        ret->set_left(do_operator(operand));
    }
    // Left operand is a variable
    else if (operand_type == "v") {
        // Set it to variable
        ret->set_left(new ValueNode(operand->FirstChild()->Value()));
    }
    // Left oprand is a constant
    else if (operand_type == "c") {
        // Set it to variable (has to be int)
        std::string val(operand->FirstChild()->Value());
        int i;
        // Parse int
        sscanf(val.c_str(), "%d", &i);
        ret->set_left(new ValueNode(i));
    }

    // Add right operand
    // Focus on right operand
    operand = operand->NextSiblingElement();
    operand_type = std::string(operand->Value());
    // Right operand is an operation (e.g. +, -, <)
    if (operand_type == "o") {
        ret->set_right(do_operator(operand));
    }
    // Right operand is a variable
    else if (operand_type == "v") {
        // Set it to variable
        ret->set_right(new ValueNode(operand->FirstChild()->Value()));
    }
    // Right oprand is a constant
    else if (operand_type == "c") {
        // Set it to variable (has to be int)
        std::string val(operand->FirstChild()->Value());
        int i;
        // Parse int
        sscanf(val.c_str(), "%d", &i);
        ret->set_right(new ValueNode(i));
    }

    return ret;
}   /* }}} */

string AST::to_string() {   /* {{{ */
    string ret = "";
    list<Node*>::iterator it;
    for (it = statements.begin(); it != statements.end(); it++) {
        ret += (*it)->to_string() + "\n";
    }

    return ret;
}   /* }}} */

/* vim: foldmethod=marker foldmarker={{{,}}} expandtab tw=78 sw=4 */
