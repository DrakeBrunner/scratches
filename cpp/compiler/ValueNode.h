#include "Node.h"
#include <string>

using namespace std;

class ValueNode : public Node {
public:
    ValueNode(int val);
    ValueNode(string var_name, int reg_number);

    bool is_var();
    string get_var_name();
    int get_reg_number();
    int get_value();
    string to_string();

private:
    int reg_number;
    /* int for now */
    int value;
    string var_name;
};
