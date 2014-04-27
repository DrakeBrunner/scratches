#include "Node.h"
#include <string>

using namespace std;

class ValueNode : public Node {
public:
    ValueNode(int val);
    ValueNode(string var_name);

    string get_var_name();
    string to_string();

private:
    int reg_number;
    /* int for now */
    int value;
    string var_name;
};
